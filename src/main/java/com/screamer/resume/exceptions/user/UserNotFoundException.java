package com.screamer.resume.exceptions.user;

public class UserNotFoundException extends Exception {
    private final String userId;

    public UserNotFoundException(String id) {
        super(String.format("User with id %s doesnt exist", id));
        this.userId = id;
    }

    public String getUserId() {
        return userId;
    }
}
