package com.screamer.resume.controller.restController;

import com.screamer.resume.config.JwtDecoderTestConfig;
import com.screamer.resume.entity.Message;
import com.screamer.resume.service.businessServices.message.MessageService;
import com.screamer.resume.utils.JsonConverter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Import({JwtDecoderTestConfig.class, JsonConverter.class})
@AutoConfigureMockMvc
class MessageRestControllerTest {

    @Autowired
    private JsonConverter jsonConverter;

    @MockBean
    private MessageService messageService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllMessage() throws Exception {
        List<Message> messageList = new ArrayList<>();
        messageList.add(new Message());
        messageList.add(new Message());

        when(messageService.getAllMessage()).thenReturn(messageList);

        MvcResult result = this.mockMvc
                .perform(
                        get("/message")
                                .header(HttpHeaders.AUTHORIZATION, "Bearer token"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        List<Message> messages = jsonConverter.convertJsonToListOfObject(contentAsString, Message.class);

        assertEquals(messageList.size(), messages.size(), "message list do not match");
    }

    @Test
    void getAllMessage_withoutJwt() throws Exception {
        this.mockMvc
                .perform(
                        get("/message"))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    void getPublicMessage() throws Exception {
        List<Message> messageList = new ArrayList<>();
        messageList.add(new Message());
        messageList.add(new Message());

        when(messageService.getPublicMessage()).thenReturn(messageList);

        MvcResult result = this.mockMvc
                .perform(
                        get("/message/public")
                                .header(HttpHeaders.AUTHORIZATION, "Bearer token"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        List<Message> messages = jsonConverter.convertJsonToListOfObject(contentAsString, Message.class);

        assertEquals(messageList.size(), messages.size(), "message list do not match");
    }

    @Test
    void getPublicMessage_withoutJwt() throws Exception {
        List<Message> messageList = new ArrayList<>();
        messageList.add(new Message());
        messageList.add(new Message());

        when(messageService.getPublicMessage()).thenReturn(messageList);

        MvcResult result = this.mockMvc
                .perform(
                        get("/message/public"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        List<Message> messages = jsonConverter.convertJsonToListOfObject(contentAsString, Message.class);

        assertEquals(messageList.size(), messages.size(), "message list do not match");
    }


    @Test
    void createNewMessage() throws Exception {
        Message mockMessage = new Message();
        String mockMessageJson = jsonConverter.convertObjectToJSON(mockMessage);

        when(messageService.createNewMessage(any(Authentication.class), any(Message.class)))
                .thenAnswer(m -> m.getArguments()[1]);

        MvcResult result = this.mockMvc
                .perform(
                        post("/message")
                                .header(HttpHeaders.AUTHORIZATION, "Bearer token")
                                .contentType(jsonConverter.getContentType())
                                .content(mockMessageJson))
                .andExpect(status().isCreated())
                .andDo(print())
                .andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Message message = jsonConverter.convertJsonToObject(contentAsString, Message.class);

        assertEquals(mockMessage.get_id(), message.get_id(), "message id do not match");
    }

    @Test
    void createNewMessage_withoutJwt() throws Exception {
        Message mockMessage = new Message();
        String mockMessageJson = jsonConverter.convertObjectToJSON(mockMessage);

        when(messageService.createNewMessage(any(), any(Message.class)))
                .thenAnswer(m -> m.getArguments()[1]);

        MvcResult result = this.mockMvc
                .perform(
                        post("/message")
                                .contentType(jsonConverter.getContentType())
                                .content(mockMessageJson))
                .andExpect(status().isCreated())
                .andDo(print())
                .andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Message message = jsonConverter.convertJsonToObject(contentAsString, Message.class);

        assertEquals(mockMessage.get_id(), message.get_id(), "message id do not match");
    }

    @Test
    void updateMessage() throws Exception {
        Message mockMessage = new Message();
        String mockMessageJson = jsonConverter.convertObjectToJSON(mockMessage);

        when(messageService.updateMessage(any(Message.class)))
                .thenAnswer(m -> m.getArguments()[0]);

        MvcResult result = this.mockMvc
                .perform(
                        put("/message")
                                .header(HttpHeaders.AUTHORIZATION, "Bearer token")
                                .contentType(jsonConverter.getContentType())
                                .content(mockMessageJson))
                .andExpect(status().isCreated())
                .andDo(print())
                .andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Message message = jsonConverter.convertJsonToObject(contentAsString, Message.class);

        assertEquals(mockMessage.get_id(), message.get_id(), "message id do not match");
    }

    @Test
    void updateMessage_withoutJwt() throws Exception {
        Message mockMessage = new Message();
        String mockMessageJson = jsonConverter.convertObjectToJSON(mockMessage);

        this.mockMvc
                .perform(
                        put("/message")
                                .contentType(jsonConverter.getContentType())
                                .content(mockMessageJson))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    void deleteMessageById() throws Exception {
        String messageId = UUID.randomUUID().toString();

        this.mockMvc
                .perform(
                        delete("/message/".concat(messageId))
                                .header(HttpHeaders.AUTHORIZATION, "Bearer token"))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    void deleteMessageById_withoutJwt() throws Exception {
        String messageId = UUID.randomUUID().toString();

        this.mockMvc
                .perform(
                        delete("/message/".concat(messageId)))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    void deleteAllMessages() throws Exception {
        this.mockMvc
                .perform(
                        delete("/message")
                                .header(HttpHeaders.AUTHORIZATION, "Bearer token"))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    void deleteAllMessages_withoutJwt() throws Exception {
        this.mockMvc
                .perform(
                        delete("/message"))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }
}