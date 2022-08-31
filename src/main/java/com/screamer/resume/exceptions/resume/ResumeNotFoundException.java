package com.screamer.resume.exceptions.resume;

public class ResumeNotFoundException extends Exception {
    private final String resumeId;

    public ResumeNotFoundException(String id) {
        super(String.format("Resume with id %s doesnt exist", id));
        this.resumeId = id;
    }

    public String getResumeId() {
        return resumeId;
    }
}
