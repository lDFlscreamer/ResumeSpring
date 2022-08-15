package com.screamer.resume.service.message;

import com.screamer.resume.entity.Message;
import com.screamer.resume.entity.MessageDTO;

import java.util.List;

public interface MessageDbService {
    List<Message> getAllSavedMessages();

    List<Message> getAllUnreadMessage();

    Message saveNewMessage(Message m);

    Message saveNewMessage(MessageDTO messageDTO);

    void deleteMessage(String messageId);

    void deleteAllMessage();

    Message updateMessage(Message message);
}
