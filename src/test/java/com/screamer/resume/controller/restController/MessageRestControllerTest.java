package com.screamer.resume.controller.restController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.screamer.resume.entity.Message;
import com.screamer.resume.service.message.MessageDbService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MessageRestControllerTest {

    @MockBean
    MessageDbService messageDbService;
    @Autowired
    private MockMvc mockMvc;
    private final MediaType APPLICATION_JSON_UTF8 = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            StandardCharsets.UTF_8);

    @Test
    @DisplayName("Get /message Test")
    void getAllMessage() throws Exception {
        when(messageDbService.getAllSavedMessages()).thenReturn(new ArrayList<>());

        this.mockMvc
                .perform(get("/message"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("post /message tests")
    void createNewMessage() throws Exception {
        Message message = createTestMessage();
        when(messageDbService.saveNewMessage(any(Message.class))).thenReturn(message);
        String requestJson = convertMessageDtoToJSON(message);

        this.mockMvc
                .perform(post("/message")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    private Message createTestMessage() {
        Message message = new Message();
        message.setAuthor("authorTest");
        message.setTitle("titleTest");
        message.setContent("contentTest");
        return message;
    }

    private String convertMessageDtoToJSON(Message message) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(message);
    }

    @Test
    @DisplayName("PUT /message/{id} test")
    void updateMessage() throws Exception {
        Message message = createTestMessage();
        when(messageDbService.updateMessage(any(Message.class))).thenReturn(message);
        String requestJson = convertMessageDtoToJSON(message);

        this.mockMvc
                .perform(put("/message")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("DELETE /message/{id} test")
    void deleteMessageById() throws Exception {
        String messageId = UUID.randomUUID().toString();

        this.mockMvc
                .perform(delete("/message/".concat(messageId)))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("Delete all message test")
    void deleteAllMessages() throws Exception {

        this.mockMvc
                .perform(delete("/message"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

}