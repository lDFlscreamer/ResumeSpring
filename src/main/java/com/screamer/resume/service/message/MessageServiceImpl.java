package com.screamer.resume.service.message;

import com.screamer.resume.entity.Message;
import com.screamer.resume.service.dbService.message.MessageDbService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageDbService messageDbService;

    public MessageServiceImpl(MessageDbService messageDbService) {
        this.messageDbService = messageDbService;
    }

    @Override
    public List<Message> getAllMessage() {
        return messageDbService.getAllSavedMessages();
    }

    @Override
    public List<Message> getPublicMessage() {
        return messageDbService.getPublicMessage();
    }

    @Override
    public Message createNewMessage(Authentication authentication, Message message) {
        if (authentication == null) {
            return messageDbService.saveNewMessage(message);
        }
        return messageDbService.saveNewMessage(authentication.getName(), message);
    }

    @Override
    public Message updateMessage(Message message) {
        return messageDbService.updateMessage(message);
    }

    @Override
    public void deleteMessageById(String id) {
        messageDbService.deleteMessage(id);
    }

    @Override
    public void deleteAllMessages() {
        messageDbService.deleteAllMessage();
    }
}
