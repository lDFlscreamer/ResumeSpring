package com.screamer.resume.service.resume;

import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class ResumeBase64Encoder {




    public Object encode(byte[] bytes) {
        byte[] encodedBytes = Base64.getEncoder().encode(bytes);
        return new String(encodedBytes);
    }
}
