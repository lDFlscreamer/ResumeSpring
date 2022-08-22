package com.screamer.resume.service.message;

import com.screamer.resume.entity.Message;

import java.util.List;

public interface MessageDbGetService {
    List<Message> getAllSavedMessages();

    List<Message> getAllSavedMessages(String author);

    List<Message> getAllUnreadMessage();

    List<Message> getAllUnreadMessage(String author);

    List<Message> getPublicMessage();

}
