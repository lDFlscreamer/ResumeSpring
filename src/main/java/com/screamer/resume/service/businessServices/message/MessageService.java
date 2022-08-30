package com.screamer.resume.service.businessServices.message;

import com.screamer.resume.entity.Message;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface MessageService {
    List<Message> getAllMessage();

    List<Message> getPublicMessage();

    Message createNewMessage(Authentication authentication, Message message);

    Message updateMessage(Message message);

    void deleteMessageById(String id);

    void deleteAllMessages();
}
