package com.screamer.resume.service.message;

import com.screamer.resume.entity.Message;

import java.util.List;

public interface MessageDbService extends MessageDbGetService {

    Message saveNewMessage(Message m);
    Message saveNewMessage(String authorId, Message m);


    void deleteMessage(String messageId);

    void deleteAllMessage();

    Message updateMessage(Message message);
}
