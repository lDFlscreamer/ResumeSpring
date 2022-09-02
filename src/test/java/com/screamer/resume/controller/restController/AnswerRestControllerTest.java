package com.screamer.resume.controller.restController;

import com.screamer.resume.entity.Message;
import com.screamer.resume.exceptions.message.MessageNotFoundException;
import com.screamer.resume.exceptions.message.MessageUnansweredException;
import com.screamer.resume.service.businessServices.answer.AnswerService;
import com.screamer.resume.service.businessServices.answer.AnswerServiceImpl;
import com.screamer.resume.service.dbServices.message.MessageDbServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith({SpringExtension.class})
class AnswerRestControllerTest {

    @Mock
    private AnswerService answerService;

    @InjectMocks
    private AnswerRestController controller;

    @Test
    void answerToMessage() throws MessageNotFoundException {
        String messageId = UUID.randomUUID().toString();
        String answerText = "text";
        Message mockMessage=mock(Message.class);

        when(answerService.answerToMessage(messageId,answerText)).thenReturn(mockMessage);

        Message message = controller.answerToMessage(messageId, answerText);

        assertEquals(mockMessage,message,"Message do not match");
    }

    @Test
    void answerToMessage_withoutMessage() throws MessageNotFoundException {
        String messageId = UUID.randomUUID().toString();
        String answerText = "text";

        when(answerService.answerToMessage(messageId,answerText)).thenThrow(new MessageNotFoundException(messageId));

        Executable executable = () -> controller.answerToMessage(messageId, answerText);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, executable);

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus(), "Response status do not match");
    }

    @Test
    void updateAnswer() throws MessageNotFoundException, MessageUnansweredException {
        String messageId = UUID.randomUUID().toString();
        String answerText = "text";
        Message mockMessage=mock(Message.class);

        when(answerService.updateAnswer(messageId,answerText)).thenReturn(mockMessage);

        Message message = controller.updateAnswer(messageId, answerText);

        assertEquals(mockMessage,message,"Message do not match");
    }

    @Test
    void updateAnswer_withoutMessage() throws MessageUnansweredException, MessageNotFoundException {
        String messageId = UUID.randomUUID().toString();
        String answerText = "text";

        when(answerService.updateAnswer(messageId,answerText)).thenThrow(new MessageNotFoundException(messageId));

        Executable executable = () -> controller.updateAnswer(messageId, answerText);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, executable);

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus(), "Response status do not match");
    }

    @Test
    void updateAnswer_withoutAnswer() throws MessageNotFoundException, MessageUnansweredException {
        String messageId = UUID.randomUUID().toString();
        String answerText = "text";

        when(answerService.updateAnswer(messageId,answerText)).thenThrow(new MessageUnansweredException(messageId));

        Executable executable = () -> controller.updateAnswer(messageId, answerText);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, executable);

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus(), "Response status do not match");
    }
}