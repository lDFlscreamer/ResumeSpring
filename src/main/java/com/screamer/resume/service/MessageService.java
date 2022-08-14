package com.screamer.resume.service;

import com.screamer.resume.entity.Message;
import com.screamer.resume.entity.MessageDTO;

import java.util.List;

public interface MessageService {
    List<Message> getAllSavedMessages();

    List<Message> getAllUnreadMessage();

    Message saveNewMessage(Message m);

    Message saveNewMessage(MessageDTO messageDTO);

    void deleteMessage(String messageId);

    void deleteAllMessage();

    Message updateMessage(Message message);
}
