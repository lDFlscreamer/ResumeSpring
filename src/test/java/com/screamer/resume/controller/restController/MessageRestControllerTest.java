package com.screamer.resume.controller.restController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.screamer.resume.entity.Message;
import com.screamer.resume.entity.MessageDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MessageRestControllerTest {


    // TODO: 12.08.2022 Use embedded mongo DB

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("Get all message Test")
    void getMessageEndpointTest() throws Exception {
        this.mockMvc
                .perform(get("/message"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


    @Test
    @DisplayName("Create and delete message tests")
    @Transactional
    void createAndDeleteMessage() throws Exception {
        Message createdMessage = createMessage();
        deleteMessageByIdEndpointTest(createdMessage.get_id());

    }

    private Message createMessage() throws Exception {
        String requestJson = convertMessageDTOToJSON();
        MvcResult mvcResult = createEndpointTest(requestJson);
        String createdJson = mvcResult.getResponse().getContentAsString();
        return convertJsonToMessage(createdJson);

    }

    private MessageDTO createTestMessageDTO() {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.author = "authorTest";
        messageDTO.title = "titleTest";
        messageDTO.content = "contentTest";
        return messageDTO;
    }

    private String convertMessageDTOToJSON() throws JsonProcessingException {
        MessageDTO messageDTO = createTestMessageDTO();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(messageDTO);

    }

    private Message convertJsonToMessage(String messageJSON) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        return mapper.readValue(messageJSON, Message.class);
    }

    private MvcResult createEndpointTest(String messageJson) throws Exception {
        MediaType APPLICATION_JSON_UTF8 = new MediaType(
                MediaType.APPLICATION_JSON.getType(),
                MediaType.APPLICATION_JSON.getSubtype(),
                StandardCharsets.UTF_8);

        return this.mockMvc
                .perform(post("/message")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(messageJson))
                .andExpect(status().isCreated())
                .andReturn();
    }

    private void deleteMessageByIdEndpointTest(String messageId) throws Exception {
        this.mockMvc
                .perform(delete("/message/".concat(messageId)))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Delete all message test")
    @Disabled
    void deleteAllMessagesEndpointTest() throws Exception {
        this.mockMvc
                .perform(delete("/message"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}