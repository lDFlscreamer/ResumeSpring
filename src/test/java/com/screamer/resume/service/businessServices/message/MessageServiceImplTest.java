package com.screamer.resume.service.businessServices.message;

import com.screamer.resume.entity.Message;
import com.screamer.resume.entity.User;
import com.screamer.resume.service.dbServices.message.MessageDbServiceImpl;
import com.screamer.resume.service.dbServices.user.UserDbServiceImpl;
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
class MessageServiceImplTest {

    @Mock
    MessageDbServiceImpl messageDbService;

    @Mock
    UserDbServiceImpl userDbService;

    @InjectMocks
    MessageServiceImpl messageService;

    @Test
    void getAllMessage() {
        List<Message> messageList = new ArrayList<>();
        messageList.add(mock(Message.class));
        messageList.add(mock(Message.class));

        when(messageDbService.getAllSavedMessages()).thenReturn(messageList);

        List<Message> allSavedMessages = messageService.getAllMessage();

        verify(messageDbService, times(1)).getAllSavedMessages();
        assertEquals(allSavedMessages, messageList, "received messages are different");
    }

    @Test
    void getPublicMessage() {
        List<Message> messageList = new ArrayList<>();
        messageList.add(mock(Message.class));
        messageList.add(mock(Message.class));

        when(messageDbService.getPublicMessage()).thenReturn(messageList);

        List<Message> publicMessage = messageService.getPublicMessage();

        verify(messageDbService, times(1)).getPublicMessage();
        assertEquals(publicMessage, messageList, "received messages are different");
    }

    @Test
    void createNewMessage() {
        Message mockMessage = mock(Message.class);
        Authentication auth = mock(Authentication.class);
        String authName = "authName";
        User mockUser = mock(User.class);

        when(auth.getName()).thenReturn(authName);
        when(messageDbService.saveNewMessage(authName, mockMessage)).thenReturn(mockMessage);
        when(userDbService.getOrCreate(authName)).thenReturn(mockUser);

        Message message = messageService.createNewMessage(auth, mockMessage);

        verify(messageDbService, times(1)).saveNewMessage(authName, mockMessage);
        assertEquals(message, mockMessage, "Message do not match");
    }

    @Test
    void updateMessage() {
        Message mockMessage = mock(Message.class);

        when(messageDbService.updateMessage(mockMessage)).thenReturn(mockMessage);

        Message message = messageService.updateMessage(mockMessage);

        verify(messageDbService, times(1)).updateMessage(mockMessage);
        assertEquals(message, mockMessage, "Message do not match");
    }

    @Test
    void deleteMessageById() {
        String messageId = UUID.randomUUID().toString();

        messageService.deleteMessageById(messageId);

        verify(messageDbService, times(1)).deleteMessage(messageId);
    }

    @Test
    void deleteAllMessages() {

        messageService.deleteAllMessages();

        verify(messageDbService, times(1)).deleteAllMessage();
    }
}