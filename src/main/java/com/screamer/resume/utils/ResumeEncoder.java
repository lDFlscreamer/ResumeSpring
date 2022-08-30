package com.screamer.resume.utils;

import com.screamer.resume.exceptions.resume.DecodeInputTypeException;

public interface ResumeEncoder {
    Object encode(byte[] bytes);

    byte[] decode(Object toDecode) throws DecodeInputTypeException;
}
