package com.screamer.resume.controller.restController;

import com.screamer.resume.config.JwtDecoderTestConfig;
import com.screamer.resume.entity.Message;
import com.screamer.resume.exceptions.message.MessageNotFoundException;
import com.screamer.resume.exceptions.message.MessageUnansweredException;
import com.screamer.resume.service.businessServices.answer.AnswerService;
import com.screamer.resume.utils.JsonConverter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Import({JwtDecoderTestConfig.class, JsonConverter.class})
@AutoConfigureMockMvc
class AnswerRestControllerMvcTest {
    @Autowired
    private JsonConverter jsonConverter;

    @MockBean
    private AnswerService answerService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void answerToMessage() throws Exception {
        String messageId = UUID.randomUUID().toString();
        String answerText = "text";
        Message mockMessage = new Message();

        when(answerService.answerToMessage(messageId, answerText)).thenReturn(mockMessage);

        MvcResult result = this.mockMvc
                .perform(
                        post("/message/".concat(messageId).concat("/answer"))
                                .header(HttpHeaders.AUTHORIZATION, "Bearer token")
                                .content(answerText))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Message message = jsonConverter.convertJsonToObject(contentAsString, Message.class);

        assertEquals(mockMessage.get_id(), message.get_id(), "Message id do not match");
    }

    @Test
    void answerToMessage_withoutJwt() throws Exception {
        String messageId = UUID.randomUUID().toString();
        String answerText = "text";
        Message mockMessage = new Message();

        when(answerService.answerToMessage(messageId, answerText)).thenReturn(mockMessage);

        this.mockMvc
                .perform(
                        post("/message/".concat(messageId).concat("/answer"))
                                .content(answerText))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    void answerToMessage_withoutMessage() throws Exception {
        String messageId = UUID.randomUUID().toString();
        String answerText = "text";

        when(answerService.answerToMessage(messageId, answerText)).thenThrow(new MessageNotFoundException(messageId));

        this.mockMvc
                .perform(
                        post("/message/".concat(messageId).concat("/answer"))
                                .header(HttpHeaders.AUTHORIZATION, "Bearer token")
                                .content(answerText))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void updateAnswer() throws Exception {
        String messageId = UUID.randomUUID().toString();
        String answerText = "text";
        Message mockMessage = new Message();

        when(answerService.updateAnswer(messageId, answerText)).thenReturn(mockMessage);

        MvcResult result = this.mockMvc
                .perform(
                        put("/message/".concat(messageId).concat("/answer"))
                                .header(HttpHeaders.AUTHORIZATION, "Bearer token")
                                .content(answerText))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Message message = jsonConverter.convertJsonToObject(contentAsString, Message.class);

        assertEquals(mockMessage.get_id(), message.get_id(), "Message id do not match");
    }

    @Test
    void updateAnswer_withoutJwt() throws Exception {
        String messageId = UUID.randomUUID().toString();
        String answerText = "text";
        Message mockMessage = new Message();

        when(answerService.updateAnswer(messageId, answerText)).thenReturn(mockMessage);

        this.mockMvc
                .perform(
                        put("/message/".concat(messageId).concat("/answer"))
                                .content(answerText))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    void updateAnswer_withoutMessage() throws Exception {
        String messageId = UUID.randomUUID().toString();
        String answerText = "text";

        when(answerService.updateAnswer(messageId, answerText)).thenThrow(new MessageNotFoundException(messageId));

        this.mockMvc
                .perform(
                        put("/message/".concat(messageId).concat("/answer"))
                                .header(HttpHeaders.AUTHORIZATION, "Bearer token")
                                .content(answerText))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void updateAnswer_withoutAnswer() throws Exception {
        String messageId = UUID.randomUUID().toString();
        String answerText = "text";

        when(answerService.updateAnswer(messageId, answerText)).thenThrow(new MessageUnansweredException(messageId));

        this.mockMvc
                .perform(
                        put("/message/".concat(messageId).concat("/answer"))
                                .header(HttpHeaders.AUTHORIZATION, "Bearer token")
                                .content(answerText))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
}