package com.screamer.resume.utils;

import com.screamer.resume.exceptions.resume.DecodeInputTypeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Base64;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ResumeBase64EncoderTest {

    private ResumeBase64Encoder encoder;

    @BeforeEach
    void setUp() {
        this.encoder = new ResumeBase64Encoder();
    }

    @Test
    void encode() {
        Random random = new Random();
        int arraySize = 1 + random.nextInt(5);
        byte[] fileBytes = new byte[arraySize];
        random.nextBytes(fileBytes);

        byte[] base64Encoded = Base64.getEncoder().encode(fileBytes);
        Object expected = new String(base64Encoded);

        Object actual = encoder.encode(fileBytes);

        assertEquals(expected, actual, "encoded data do not match");
    }

    @Test
    void decode() throws DecodeInputTypeException {
        Random random = new Random();
        int arraySize = 1 + random.nextInt(5);
        byte[] fileBytes = new byte[arraySize];
        random.nextBytes(fileBytes);

        byte[] base64Encoded = Base64.getEncoder().encode(fileBytes);
        Object encoded = new String(base64Encoded);

        byte[] actual = encoder.decode(encoded);

        assertArrayEquals(fileBytes, actual, "encoded data do not match");
    }

    @Test
    void decode_DecodeInputType() {
        Object mockObject = mock(Object.class);

        Executable executable = () -> encoder.decode(mockObject);
        DecodeInputTypeException exception = assertThrows(DecodeInputTypeException.class, executable);

        assertEquals(mockObject, exception.getProblemObject(), "problem object do not match");
    }
}