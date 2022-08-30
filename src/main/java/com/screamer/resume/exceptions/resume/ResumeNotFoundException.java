package com.screamer.resume.exceptions.resume;

public class ResumeNotFoundException extends Exception {
    public ResumeNotFoundException(String id) {
        super(String.format("Resume with id %s doesnt exist", id));
    }
}
