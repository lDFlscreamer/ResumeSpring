package com.screamer.resume.service.message;

import com.screamer.resume.entity.Answer;
import com.screamer.resume.entity.Message;
import com.screamer.resume.exceptions.MessageNotFoundException;
import com.screamer.resume.exceptions.MessageUnansweredException;
import com.screamer.resume.repository.MessageRepository;
import com.screamer.resume.service.answer.AnswerDbServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith({SpringExtension.class})
class AnswerDbServiceTest {

    @Mock
    MessageRepository messageRepository;

    @InjectMocks
    AnswerDbServiceImpl answerDbService;

    @Test
    @DisplayName("Answer to message check")
    void answerToMessage() throws Exception {
        Message mockMessage = new Message();
        String messageId = UUID.randomUUID().toString();
        String answerText = "Answer Text";
        when(messageRepository.findById(messageId)).thenReturn(Optional.of(mockMessage));
        when(messageRepository.save(any(Message.class))).thenAnswer(m->m.getArguments()[0]);

        Message message = answerDbService.answerToMessage(messageId, answerText);

        assertNotNull(message.getAnswer(), "Answer is null");
        assertEquals(message.getAnswer().getAnswerText(), answerText, "Answer text do not match");
    }

    @Test
    @DisplayName("Answer to non-existence message check")
    void answerToMessage_shouldThrow() {
        String messageId = UUID.randomUUID().toString();
        String answerText = "Answer Text";
        when(messageRepository.findById(messageId)).thenReturn(Optional.empty());

        assertThrows(MessageNotFoundException.class, () -> {
            answerDbService.answerToMessage(messageId, answerText);
        });
    }

    @Test
    @DisplayName("Update answer check")
    void updateAnswer() throws MessageNotFoundException, MessageUnansweredException {
        Message mockMessage = new Message();
        mockMessage.setAnswer(new Answer());
        String messageId = UUID.randomUUID().toString();
        String answerText = "Answer Text";
        when(messageRepository.findById(messageId)).thenReturn(Optional.of(mockMessage));
        when(messageRepository.save(any(Message.class))).thenAnswer(m->m.getArguments()[0]);

        Message message = answerDbService.updateAnswer(messageId, answerText);

        assertNotNull(message.getAnswer(), "Answer is null");
        assertEquals(message.getAnswer().getAnswerText(), answerText, "Answer text do not match");
    }


    @Test
    @DisplayName("Update non-existence answer check")
    void updateAnswer_shouldThrow_whenMessageIsNull() {
        String messageId = UUID.randomUUID().toString();
        String answerText = "Answer Text";
        when(messageRepository.findById(messageId)).thenReturn(Optional.empty());

        assertThrows(MessageNotFoundException.class, () -> {
            answerDbService.updateAnswer(messageId, answerText);
        });
    }

    @Test
    @DisplayName("Update non-existence answer check")
    void updateAnswer_shouldThrow_whenAnswerIsNull() {
        Message mockMessage = new Message();
        String messageId = UUID.randomUUID().toString();
        String answerText = "Answer Text";
        when(messageRepository.findById(messageId)).thenReturn(Optional.of(mockMessage));

        assertThrows(MessageUnansweredException.class, () -> {
            answerDbService.updateAnswer(messageId, answerText);
        });
    }
}