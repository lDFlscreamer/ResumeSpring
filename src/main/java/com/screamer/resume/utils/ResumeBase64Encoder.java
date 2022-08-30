package com.screamer.resume.utils;

import com.screamer.resume.exceptions.resume.DecodeInputTypeException;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class ResumeBase64Encoder implements ResumeEncoder {
    @Override
    public Object encode(byte[] bytes) {
        byte[] encodedBytes = Base64.getEncoder().encode(bytes);
        return new String(encodedBytes);
    }

    @Override
    public byte[] decode(Object toDecode) throws DecodeInputTypeException {
        if (!(toDecode instanceof String)) {
            throw new DecodeInputTypeException(toDecode);
        }
        String decodeString = (String) toDecode;
        return Base64.getDecoder().decode(decodeString.getBytes());
    }
}
