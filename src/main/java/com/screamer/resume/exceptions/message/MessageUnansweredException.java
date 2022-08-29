package com.screamer.resume.exceptions.message;

public class MessageUnansweredException extends Exception {
    public MessageUnansweredException(String id) {
        super(String.format("Messages with id %s doesnt have answer", id));
    }
}
