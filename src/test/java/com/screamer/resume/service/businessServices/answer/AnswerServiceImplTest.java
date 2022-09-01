package com.screamer.resume.service.businessServices.answer;

import com.screamer.resume.entity.Answer;
import com.screamer.resume.entity.Message;
import com.screamer.resume.exceptions.message.MessageNotFoundException;
import com.screamer.resume.exceptions.message.MessageUnansweredException;
import com.screamer.resume.service.dbServices.message.MessageDbServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith({SpringExtension.class})
class AnswerServiceImplTest {
    @Mock
    private MessageDbServiceImpl messageDbService;

    @InjectMocks
    private AnswerServiceImpl answerService;

    @Test()
    void answerToMessage() throws MessageNotFoundException {
        Message mockMessage = new Message();
        String messageId = UUID.randomUUID().toString();
        String answerText = "Answer Text";

        when(messageDbService.getMessageById(messageId)).thenReturn(mockMessage);
        when(messageDbService.updateMessage(any(Message.class))).thenAnswer(m -> m.getArguments()[0]);

        Message message = answerService.answerToMessage(messageId, answerText);

        verify(messageDbService, times(1)).getMessageById(messageId);
        verify(messageDbService, times(1)).updateMessage(any(Message.class));
        assertNotNull(message.getAnswer(), "Answer is null");
        assertEquals(answerText, message.getAnswer().getAnswerText(), "Answer text do not match");
    }

    @Test()
    void answerToMessage_MessageNotFound() throws MessageNotFoundException {
        String messageId = UUID.randomUUID().toString();
        String answerText = "Answer Text";

        MessageNotFoundException exception = new MessageNotFoundException(messageId);
        when(messageDbService.getMessageById(messageId)).thenThrow(exception);

        Executable executable = () -> answerService.answerToMessage(messageId, answerText);
        MessageNotFoundException notFoundException = assertThrows(MessageNotFoundException.class, executable);

        verify(messageDbService, times(1)).getMessageById(messageId);
        verify(messageDbService, times(0)).updateMessage(any(Message.class));
        assertEquals(messageId, notFoundException.getMessageId(), "message id do not match");
    }

    @Test
    void updateAnswer() throws MessageNotFoundException, MessageUnansweredException {
        Message mockMessage = new Message();
        mockMessage.setAnswer(new Answer());
        String messageId = UUID.randomUUID().toString();
        String answerText = "Answer Text";

        when(messageDbService.getMessageById(messageId)).thenReturn(mockMessage);
        when(messageDbService.updateMessage(any(Message.class))).thenAnswer(m -> m.getArguments()[0]);

        Message message = answerService.updateAnswer(messageId, answerText);

        verify(messageDbService, times(1)).getMessageById(messageId);
        verify(messageDbService, times(1)).updateMessage(any(Message.class));
        assertNotNull(message.getAnswer(), "Answer is null");
        assertEquals(answerText, message.getAnswer().getAnswerText(), "Answer text do not match");
    }

    @Test
    void updateAnswer_MessageNotFound() throws MessageNotFoundException {
        Message mockMessage = new Message();
        mockMessage.setAnswer(new Answer());
        String messageId = UUID.randomUUID().toString();
        String answerText = "Answer Text";

        MessageNotFoundException exception = new MessageNotFoundException(messageId);
        when(messageDbService.getMessageById(messageId)).thenThrow(exception);

        Executable executable = () -> answerService.updateAnswer(messageId, answerText);
        MessageNotFoundException notFoundException = assertThrows(MessageNotFoundException.class, executable);

        verify(messageDbService, times(1)).getMessageById(messageId);
        verify(messageDbService, times(0)).updateMessage(any(Message.class));
        assertEquals(messageId, notFoundException.getMessageId(), "message id do not match");
    }

    @Test
    void updateAnswer_MessageUnanswered() throws MessageNotFoundException {
        Message mockMessage = new Message();
        String messageId = UUID.randomUUID().toString();
        String answerText = "Answer Text";

        when(messageDbService.getMessageById(messageId)).thenReturn(mockMessage);
        when(messageDbService.updateMessage(any(Message.class))).thenAnswer(m -> m.getArguments()[0]);

        Executable executable = () -> answerService.updateAnswer(messageId, answerText);
        MessageUnansweredException unansweredException = assertThrows(MessageUnansweredException.class, executable);

        verify(messageDbService, times(1)).getMessageById(messageId);
        verify(messageDbService, times(0)).updateMessage(any(Message.class));
        assertEquals(messageId, unansweredException.getMessageId(), "message id do not match");
    }
}