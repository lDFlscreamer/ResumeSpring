package com.screamer.resume.service;

import com.screamer.resume.entity.Message;
import com.screamer.resume.entity.MessageDTO;
import com.screamer.resume.repository.MessageRepository;
import com.screamer.resume.service.message.MessageDbServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class MessageDbServiceImplTest {

    @Mock
    MessageRepository messageRepository;

    @InjectMocks
    MessageDbServiceImpl messageService;

    @Test
    @DisplayName("Test method to get all messages")
    void getAllSavedMessages() {
        List<Message> messageList = new ArrayList<>();
        messageList.add(mock(Message.class));
        messageList.add(mock(Message.class));
        when(messageRepository.findAll()).thenReturn(messageList);

        List<Message> allSavedMessages = messageService.getAllSavedMessages();

        assertEquals(allSavedMessages.size(), messageList.size(), "All message list size was changed");
        assertEquals(allSavedMessages, messageList, "received messages are different");
    }

    @Test
    @DisplayName("Test method to get all unread messages")
    void getAllUnreadMessage() {
        List<Message> messageList = new ArrayList<>();
        messageList.add(mock(Message.class));
        messageList.add(mock(Message.class));
        when(messageRepository.findAllByReadIsFalse()).thenReturn(messageList);

        List<Message> allUnreadMessage = messageService.getAllUnreadMessage();

        assertEquals(allUnreadMessage.size(), messageList.size(), "Unread message list size was changed");
        assertEquals(allUnreadMessage, messageList);
    }

    @Test
    @DisplayName("Test method to save message")
    void saveNewMessage() {
        Message mockMessage = mock(Message.class);
        when(messageRepository.save(mockMessage)).thenReturn(mockMessage);

        Message savedMessage = messageService.saveNewMessage(mockMessage);

        assertEquals(mockMessage, savedMessage);
    }


    @Test
    @DisplayName("Test method to save message by pass messageDTO")
    void testSaveNewMessage() {
        MessageDTO mockMessageDto = mock(MessageDTO.class);
        Message mockMessage = new Message(mockMessageDto);
        when(messageRepository.save(mockMessage)).thenReturn(mockMessage);

        Message savedMessage = messageService.saveNewMessage(mockMessageDto);

        assertEquals(mockMessage, savedMessage);
    }

    @Test
    @DisplayName("Test method to update message")
    void updateMessage() {
        Message mockMessage = mock(Message.class);
        when(messageRepository.save(mockMessage)).thenReturn(mockMessage);

        Message message = messageService.updateMessage(mockMessage);

        assertEquals(mockMessage, message);
    }

    @Test
    @DisplayName("Test method to delete message")
    void deleteMessage() {
        Message mockMessage = mock(Message.class);

        messageService.deleteMessage(mockMessage.get_id());
    }

    @Test
    @DisplayName("Test method to delete all messages")
    void deleteAllMessage() {
        messageService.deleteAllMessage();
    }

}