package com.screamer.resume.service.dbService.message;

import com.screamer.resume.entity.Message;

public interface MessageDbService extends MessageDbGetMethods {

    Message saveNewMessage(Message m);
    Message saveNewMessage(String authorId, Message m);


    void deleteMessage(String messageId);

    void deleteAllMessage();

    Message updateMessage(Message message);
}
