package com.screamer.resume.controller.restController;

import com.screamer.resume.entity.Message;
import com.screamer.resume.service.businessServices.message.MessageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith({SpringExtension.class})
class MessageRestControllerTest {
    @Mock
    private MessageService messageService;

    @InjectMocks
    private MessageRestController controller;

    @Test
    void getAllMessage() {
        List<Message> messageList = new ArrayList<>();
        messageList.add(new Message());
        messageList.add(new Message());

        when(messageService.getAllMessage()).thenReturn(messageList);

        List<Message> messages = controller.getAllMessage();

        assertEquals(messageList.size(), messages.size(), "message list do not match");
    }

    @Test
    void getPublicMessage() {
        List<Message> messageList = new ArrayList<>();
        messageList.add(new Message());
        messageList.add(new Message());

        when(messageService.getPublicMessage()).thenReturn(messageList);

        List<Message> publicMessages = controller.getPublicMessage();

        assertEquals(messageList.size(), publicMessages.size(), "message list do not match");
    }

    @Test
    void createNewMessage() {
        Message mockMessage = new Message();
        Authentication mockAuth = mock(Authentication.class);

        when(messageService.createNewMessage(mockAuth, mockMessage)).thenReturn(mockMessage);

        Message message = controller.createNewMessage(mockAuth, mockMessage);

        assertEquals(mockMessage.get_id(), message.get_id(), "message id do not match");
    }

    @Test
    void updateMessage() {
        Message mockMessage = new Message();

        when(messageService.updateMessage(mockMessage)).thenReturn(mockMessage);

        Message message = controller.updateMessage(mockMessage);

        assertEquals(mockMessage.get_id(), message.get_id(), "message id do not match");
    }

    @Test
    void deleteMessageById() {
        String messageId = UUID.randomUUID().toString();

        controller.deleteMessageById(messageId);

        verify(messageService,times(1)).deleteMessageById(messageId);
        verify(messageService,times(1)).deleteMessageById(anyString());
    }

    @Test
    void deleteAllMessages() {

        controller.deleteAllMessages();

        verify(messageService,times(1)).deleteAllMessages();
    }
}