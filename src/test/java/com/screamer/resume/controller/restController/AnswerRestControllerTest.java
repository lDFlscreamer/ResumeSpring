package com.screamer.resume.controller.restController;

import com.screamer.resume.entity.Message;
import com.screamer.resume.service.message.AnswerDbService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AnswerRestControllerTest {

    @MockBean
    AnswerDbService answerDbService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("POST /message/{id}/Answer test")
    void answerToMessage() throws Exception {
        Message message = mock(Message.class);
        String messageId = UUID.randomUUID().toString();
        when(answerDbService.answerToMessage(anyString(), anyString())).thenReturn(message);

        this.mockMvc
                .perform(post(String.format("/message/%s/Answer", messageId))
                        .content("requestJson"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("PUT /message/{id}/Answer test")
    void updateAnswer() throws Exception {
        Message message = mock(Message.class);
        String messageId = UUID.randomUUID().toString();
        when(answerDbService.updateAnswer(anyString(), anyString())).thenReturn(message);

        this.mockMvc
                .perform(put(String.format("/message/%s/Answer", messageId))
                        .content("requestJson"))
                .andExpect(status().isUnauthorized());
    }
}