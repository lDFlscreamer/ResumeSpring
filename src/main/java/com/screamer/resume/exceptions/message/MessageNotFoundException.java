package com.screamer.resume.exceptions.message;

public class MessageNotFoundException extends Exception {

    private final String messageId;

    public MessageNotFoundException(String id) {
        super(String.format("Messages with id %s doesnt exist", id));
        this.messageId = id;
    }

    public String getMessageId() {
        return messageId;
    }
}
