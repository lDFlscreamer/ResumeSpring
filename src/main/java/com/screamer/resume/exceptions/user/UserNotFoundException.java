package com.screamer.resume.exceptions.user;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String id) {
        super(String.format("User with id %s doesnt exist", id));
    }
}
