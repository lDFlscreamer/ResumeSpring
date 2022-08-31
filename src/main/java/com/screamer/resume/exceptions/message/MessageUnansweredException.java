package com.screamer.resume.exceptions.message;

public class MessageUnansweredException extends Exception {

    private final String messageId;

    public MessageUnansweredException(String id) {
        super(String.format("Messages with id %s doesnt have answer", id));
        this.messageId = id;
    }

    public String getMessageId() {
        return messageId;
    }
}
