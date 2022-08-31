package com.screamer.resume.service.dbServices.message;

import com.screamer.resume.entity.Message;
import com.screamer.resume.entity.User;
import com.screamer.resume.exceptions.message.MessageNotFoundException;
import com.screamer.resume.repository.MessageRepository;
import com.screamer.resume.service.dbServices.user.UserDbServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith({SpringExtension.class})
class MessageDbServiceImplTest {
    @Mock
    private MessageRepository messageRepository;
    @Mock
    private UserDbServiceImpl userDbService;
    @InjectMocks
    private MessageDbServiceImpl messageDbService;

    @Test
    void getAllSavedMessages() {
        List<Message> messageList = new ArrayList<>();

        when(messageRepository.findAll()).thenReturn(messageList);

        List<Message> allSavedMessages = messageDbService.getAllSavedMessages();

        verify(messageRepository, times(1)).findAll();
        assertEquals(messageList, allSavedMessages, "Message list do not match");
    }

    @Test
    void getAllSavedMessages_withAuthor() {
        String authorName = "authorName";
        List<Message> messageList = new ArrayList<>();

        when(messageRepository.findAllByAuthor_UserAuthId(authorName)).thenReturn(messageList);

        List<Message> allSavedMessages = messageDbService.getAllSavedMessages(authorName);

        verify(messageRepository, times(1)).findAllByAuthor_UserAuthId(authorName);
        assertEquals(messageList, allSavedMessages, "Message list do not match");
    }

    @Test
    void getAllUnreadMessage() {
        List<Message> messageList = new ArrayList<>();

        when(messageRepository.findAllByReadIsFalse()).thenReturn(messageList);

        List<Message> allUnreadMessage = messageDbService.getAllUnreadMessage();

        verify(messageRepository, times(1)).findAllByReadIsFalse();
        assertEquals(messageList, allUnreadMessage, "Message list do not match");
    }

    @Test
    void getAllUnreadMessage_withAuthor() {
        String authorName = "authorName";
        List<Message> messageList = new ArrayList<>();

        when(messageRepository.findAllByAuthor_UserAuthIdAndReadIsFalse(authorName)).thenReturn(messageList);

        List<Message> allUnreadMessage = messageDbService.getAllUnreadMessage(authorName);

        verify(messageRepository, times(1)).findAllByAuthor_UserAuthIdAndReadIsFalse(authorName);
        assertEquals(messageList, allUnreadMessage, "Message list do not match");
    }

    @Test
    void getPublicMessage() {
        List<Message> messageList = new ArrayList<>();

        when(messageRepository.findAllByDirectIsFalse()).thenReturn(messageList);

        List<Message> publicMessage = messageDbService.getPublicMessage();

        verify(messageRepository, times(1)).findAllByDirectIsFalse();
        assertEquals(messageList, publicMessage, "Message list do not match");

    }

    @Test
    void getMessageById() throws MessageNotFoundException {
        Message mockMessage = mock(Message.class);
        String messageId = UUID.randomUUID().toString();

        when(messageRepository.findById(messageId)).thenReturn(Optional.of(mockMessage));

        Message message = messageDbService.getMessageById(messageId);

        verify(messageRepository, times(1)).findById(messageId);
        assertEquals(mockMessage, message, "Message do not match");
    }

    @Test
    void getMessageById_MessageNotFound() {
        String messageId = UUID.randomUUID().toString();

        when(messageRepository.findById(messageId)).thenReturn(Optional.empty());

        Executable executable = () -> messageDbService.getMessageById(messageId);
        MessageNotFoundException exception = assertThrows(MessageNotFoundException.class, executable);

        verify(messageRepository, times(1)).findById(messageId);
        assertEquals(messageId, exception.getMessageId(), "Message id do not match");
    }

    @Test
    void saveNewMessage() {
        Message mockMessage = new Message();

        when(messageRepository.save(mockMessage)).thenReturn(mockMessage);

        Message message = messageDbService.saveNewMessage(mockMessage);

        verify(messageRepository, times(1)).save(mockMessage);
        assertEquals(mockMessage,message, "Message do not match");
    }

    @Test
    void saveNewMessage_withAuthor() {
        Message mockMessage = new Message();
        User mockUser = mock(User.class);
        String authorId = UUID.randomUUID().toString();

        when(userDbService.getOrCreate(authorId)).thenReturn(mockUser);
        when(messageRepository.save(mockMessage)).thenReturn(mockMessage);

        Message message = messageDbService.saveNewMessage(authorId, mockMessage);

        verify(userDbService, times(1)).getOrCreate(authorId);
        verify(messageRepository, times(1)).save(mockMessage);
        assertEquals(mockMessage,message, "Message do not match");
        assertEquals(mockUser,message.getAuthor(), "Author of message do not match");
    }

    @Test
    void updateMessage() {
        Message mockMessage = new Message();

        when(messageRepository.save(mockMessage)).thenReturn(mockMessage);

        Message message = messageDbService.updateMessage(mockMessage);

        verify(messageRepository, times(1)).save(mockMessage);
        assertEquals(mockMessage,message, "Message do not match");
    }

    @Test
    void deleteMessage() {
        String messageId = UUID.randomUUID().toString();

        messageDbService.deleteMessage(messageId);

        verify(messageRepository, times(1)).deleteById(anyString());
        verify(messageRepository, times(1)).deleteById(messageId);
    }

    @Test
    void deleteAllMessage() {

        messageDbService.deleteAllMessage();

        verify(messageRepository, times(1)).deleteAll();
    }
}