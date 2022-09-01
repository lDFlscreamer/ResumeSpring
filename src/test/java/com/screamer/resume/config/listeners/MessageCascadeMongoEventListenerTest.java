package com.screamer.resume.config.listeners;

import com.screamer.resume.entity.Answer;
import com.screamer.resume.entity.Message;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith({SpringExtension.class})
class MessageCascadeMongoEventListenerTest {

    @Mock
    private MongoOperations mongoOperations;

    @InjectMocks
    private MessageCascadeMongoEventListener messageEventListener;

    @Test
    void onBeforeConvert() {
        BeforeConvertEvent<Message> event = mock(BeforeConvertEvent.class);
        Message mockMessage = mock(Message.class);
        Answer mockAnswer = mock(Answer.class);

        when(event.getSource()).thenReturn(mockMessage);
        when(mockMessage.getAnswer()).thenReturn(mockAnswer);
        when(mongoOperations.save(any())).thenAnswer(o -> o.getArguments()[0]);

        messageEventListener.onBeforeConvert(event);

        verify(mockMessage, atLeastOnce()).getAnswer();
        verify(mongoOperations, times(1)).save(mockAnswer);
    }

    @Test
    void onBeforeConvert_withoutAnswer() {
        BeforeConvertEvent<Message> event = mock(BeforeConvertEvent.class);
        Message mockMessage = mock(Message.class);

        when(event.getSource()).thenReturn(mockMessage);
        when(mockMessage.getAnswer()).thenReturn(null);
        when(mongoOperations.save(any())).thenAnswer(o -> o.getArguments()[0]);

        messageEventListener.onBeforeConvert(event);

        verify(mockMessage, atLeastOnce()).getAnswer();
        verify(mongoOperations, never()).save(any(Answer.class));
    }

    @Test
    void onBeforeDelete() {
        BeforeDeleteEvent<Message> event = mock(BeforeDeleteEvent.class);
        Document mockDocument = mock(Document.class);
        Object documentId= mock(Object.class);
        Message mockMessage = mock(Message.class);
        Answer mockAnswer = mock(Answer.class);

        when(event.getSource()).thenReturn(mockDocument);
        when(mockDocument.get("_id")).thenReturn(documentId);
        when(mongoOperations.findById(documentId, Message.class)).thenReturn(mockMessage);
        when(mockMessage.getAnswer()).thenReturn(mockAnswer);

        messageEventListener.onBeforeDelete(event);

        verify(mongoOperations, times(1)).findById(documentId, Message.class);
        verify(mongoOperations, times(1)).remove(mockAnswer);
    }

    @Test
    void onBeforeDelete_withoutMessage() {
        BeforeDeleteEvent<Message> event = mock(BeforeDeleteEvent.class);
        Document mockDocument = mock(Document.class);
        Object documentId= mock(Object.class);

        when(event.getSource()).thenReturn(mockDocument);
        when(mockDocument.get("_id")).thenReturn(documentId);
        when(mongoOperations.findById(documentId, Message.class)).thenReturn(null);

        messageEventListener.onBeforeDelete(event);

        verify(mongoOperations, times(1)).findById(documentId, Message.class);
        verify(mongoOperations, never()).remove(any(Answer.class));
    }

    @Test
    void onBeforeDelete_withoutAnswer() {
        BeforeDeleteEvent<Message> event = mock(BeforeDeleteEvent.class);
        Document mockDocument = mock(Document.class);
        Object documentId= mock(Object.class);
        Message mockMessage = mock(Message.class);

        when(event.getSource()).thenReturn(mockDocument);
        when(mockDocument.get("_id")).thenReturn(documentId);
        when(mongoOperations.findById(documentId, Message.class)).thenReturn(mockMessage);
        when(mockMessage.getAnswer()).thenReturn(null);

        messageEventListener.onBeforeDelete(event);

        verify(mongoOperations, times(1)).findById(documentId, Message.class);
        verify(mongoOperations, never()).remove(any(Answer.class));
    }
}