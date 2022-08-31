package com.screamer.resume.entity;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnswerTest {


    @Test
    void get_id() throws NoSuchFieldException, IllegalAccessException {
        final Answer answer = new Answer();
        final String answerId = UUID.randomUUID().toString();

        final Field field = answer.getClass().getDeclaredField("_id");
        field.setAccessible(true);
        field.set(answer, answerId);

        final String actualAnswerId = answer.get_id();

        assertEquals(answerId, actualAnswerId, "Answer Id do not match");

    }

    @Test
    void set_id() throws NoSuchFieldException, IllegalAccessException {
        final Answer answer = new Answer();
        final String answerId = UUID.randomUUID().toString();

        answer.set_id(answerId);

        final Field field = answer.getClass().getDeclaredField("_id");
        field.setAccessible(true);
        assertEquals(answerId, field.get(answer), "Answer Id do not match");
    }

    @Test
    void getAnswerText() throws IllegalAccessException, NoSuchFieldException {
        final Answer answer = new Answer();
        final String answerText = UUID.randomUUID().toString();

        final Field field = answer.getClass().getDeclaredField("answerText");
        field.setAccessible(true);
        field.set(answer, answerText);

        final String actualAnswerText = answer.getAnswerText();

        assertEquals(answerText, actualAnswerText, "answerText do not match");
    }

    @Test
    void setAnswerText() throws NoSuchFieldException, IllegalAccessException {
        final Answer answer = new Answer();
        final String answerText = UUID.randomUUID().toString();

        answer.setAnswerText(answerText);

        final Field field = answer.getClass().getDeclaredField("answerText");
        field.setAccessible(true);
        assertEquals(answerText, field.get(answer), "answerText do not match");
    }

    @Test
    void getTimestamp() throws NoSuchFieldException, IllegalAccessException {
        final Answer answer = new Answer();
        final long timestamp = Long.MIN_VALUE;

        final Field field = answer.getClass().getDeclaredField("timestamp");
        field.setAccessible(true);
        field.set(answer, timestamp);

        final long actualTimestamp = answer.getTimestamp();

        assertEquals(timestamp, actualTimestamp, "timestamp do not match");
    }

    @Test
    void setTimestamp() throws NoSuchFieldException, IllegalAccessException {
        final Answer answer = new Answer();
        final long timestamp = Long.MIN_VALUE;

        answer.setTimestamp(timestamp);

        final Field field = answer.getClass().getDeclaredField("timestamp");
        field.setAccessible(true);
        assertEquals(timestamp, field.get(answer), "timestamp do not match");
    }
}