package com.screamer.resume.exceptions.resume;

public class DecodeInputTypeException extends Exception {
    private final Object problemObject;

    public DecodeInputTypeException(Object toDecode) {
        super("Cant decode,because input type mismatch ");
        this.problemObject = toDecode;
    }

    public Object getProblemObject() {
        return problemObject;
    }
}
