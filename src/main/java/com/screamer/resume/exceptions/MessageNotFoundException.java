package com.screamer.resume.exceptions;

public class MessageNotFoundException extends Exception{

    public MessageNotFoundException(String id) {
        super(String.format("Messages with id %s doesnt exist",id));
    }
}
