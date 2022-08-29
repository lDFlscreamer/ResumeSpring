package com.screamer.resume.service.dbService.message;

import com.screamer.resume.entity.Message;
import com.screamer.resume.exceptions.message.MessageNotFoundException;

import java.util.List;

public interface MessageDbGetMethods {
    List<Message> getAllSavedMessages();

    List<Message> getAllSavedMessages(String author);

    List<Message> getAllUnreadMessage();

    List<Message> getAllUnreadMessage(String author);

    List<Message> getPublicMessage();

    Message getMessageById(String messageId) throws MessageNotFoundException;

}
