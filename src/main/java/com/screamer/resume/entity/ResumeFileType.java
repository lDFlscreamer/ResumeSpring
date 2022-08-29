package com.screamer.resume.entity;

public enum ResumeFileType {
    PDF("pdf");

    private final String typeCode;

    ResumeFileType(String code) {
        this.typeCode = code;
    }

    public String getCode() {
        return typeCode;
    }
}
