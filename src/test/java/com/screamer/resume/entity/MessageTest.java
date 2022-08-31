package com.screamer.resume.entity;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class MessageTest {

    @Test
    void get_id() throws NoSuchFieldException, IllegalAccessException {
        final Message message = new Message();
        final String messageId = UUID.randomUUID().toString();

        final Field field = message.getClass().getDeclaredField("_id");
        field.setAccessible(true);
        field.set(message, messageId);

        final String actualMessageId = message.get_id();

        assertEquals(messageId, actualMessageId, "Message Id do not match");
    }

    @Test
    void set_id() throws NoSuchFieldException, IllegalAccessException {
        final Message message = new Message();
        final String messageId = UUID.randomUUID().toString();

        message.set_id(messageId);

        final Field field = message.getClass().getDeclaredField("_id");
        field.setAccessible(true);
        assertEquals(messageId, field.get(message), "Message Id do not match");
    }

    @Test
    void getAuthor() throws NoSuchFieldException, IllegalAccessException {
        final Message message = new Message();
        final User author = mock(User.class);

        final Field field = message.getClass().getDeclaredField("author");
        field.setAccessible(true);
        field.set(message, author);

        User actualAuthor = message.getAuthor();

        assertEquals(author, actualAuthor, "Author do not match");
    }

    @Test
    void setAuthor() throws NoSuchFieldException, IllegalAccessException {
        final Message message = new Message();
        final User author = mock(User.class);

        message.setAuthor(author);

        final Field field = message.getClass().getDeclaredField("author");
        field.setAccessible(true);
        assertEquals(author, field.get(message), "author  do not match");
    }

    @Test
    void getPublicAuthorName() throws NoSuchFieldException, IllegalAccessException {
        final Message message = new Message();
        final String publicAuthorName = "publicAuthorName";

        final Field field = message.getClass().getDeclaredField("publicAuthorName");
        field.setAccessible(true);
        field.set(message, publicAuthorName);

        String actualPublicAuthorName = message.getPublicAuthorName();

        assertEquals(publicAuthorName, actualPublicAuthorName, "publicAuthorName do not match");
    }

    @Test
    void setPublicAuthorName() throws NoSuchFieldException, IllegalAccessException {
        final Message message = new Message();
        final String publicAuthorName = "publicAuthorName";

        message.setPublicAuthorName(publicAuthorName);

        final Field field = message.getClass().getDeclaredField("publicAuthorName");
        field.setAccessible(true);
        assertEquals(publicAuthorName, field.get(message), "publicAuthorName  do not match");
    }

    @Test
    void getTitle() throws NoSuchFieldException, IllegalAccessException {
        final Message message = new Message();
        final String value = "title";

        final Field field = message.getClass().getDeclaredField("title");
        field.setAccessible(true);
        field.set(message, value);

        String actualValue = message.getTitle();

        assertEquals(value, actualValue, "title do not match");
    }

    @Test
    void setTitle() throws NoSuchFieldException, IllegalAccessException {
        final Message message = new Message();
        final String value = "title";

        message.setTitle(value);

        final Field field = message.getClass().getDeclaredField("title");
        field.setAccessible(true);
        assertEquals(value, field.get(message), "title  do not match");
    }

    @Test
    void getContent() throws NoSuchFieldException, IllegalAccessException {
        final Message message = new Message();
        final String value = "content";

        final Field field = message.getClass().getDeclaredField("content");
        field.setAccessible(true);
        field.set(message, value);

        String actualValue = message.getContent();

        assertEquals(value, actualValue, "content do not match");
    }

    @Test
    void setContent() throws NoSuchFieldException, IllegalAccessException {
        final Message message = new Message();
        final String value = "content";

        message.setContent(value);

        final Field field = message.getClass().getDeclaredField("content");
        field.setAccessible(true);
        assertEquals(value, field.get(message), "content  do not match");
    }

    @Test
    void getAnswer() throws NoSuchFieldException, IllegalAccessException {
        final Message message = new Message();
        final Answer value = mock(Answer.class);

        final Field field = message.getClass().getDeclaredField("answer");
        field.setAccessible(true);
        field.set(message, value);

        Answer actualValue = message.getAnswer();

        assertEquals(value, actualValue, "answer do not match");
    }

    @Test
    void setAnswer() throws NoSuchFieldException, IllegalAccessException {
        final Message message = new Message();
        final Answer value = mock(Answer.class);

        message.setAnswer(value);

        final Field field = message.getClass().getDeclaredField("answer");
        field.setAccessible(true);
        assertEquals(value, field.get(message), "answer  do not match");
    }

    @Test
    void isRead() throws NoSuchFieldException, IllegalAccessException {
        final Message message = new Message();
        final boolean value = true;

        final Field field = message.getClass().getDeclaredField("read");
        field.setAccessible(true);
        field.set(message, value);

        boolean actualValue = message.isRead();

        assertEquals(value, actualValue, "read do not match");
    }

    @Test
    void setRead() throws NoSuchFieldException, IllegalAccessException {
        final Message message = new Message();
        final boolean value = true;

        message.setRead(value);

        final Field field = message.getClass().getDeclaredField("read");
        field.setAccessible(true);
        assertEquals(value, field.get(message), "read  do not match");
    }

    @Test
    void isDirect() throws NoSuchFieldException, IllegalAccessException {
        final Message message = new Message();
        final boolean value = true;

        final Field field = message.getClass().getDeclaredField("direct");
        field.setAccessible(true);
        field.set(message, value);

        boolean actualValue = message.isDirect();

        assertEquals(value, actualValue, "direct do not match");
    }

    @Test
    void setDirect() throws NoSuchFieldException, IllegalAccessException {
        final Message message = new Message();
        final boolean value = true;

        message.setDirect(value);

        final Field field = message.getClass().getDeclaredField("direct");
        field.setAccessible(true);
        assertEquals(value, field.get(message), "direct  do not match");
    }

    @Test
    void getTimestamp() throws NoSuchFieldException, IllegalAccessException {
        final Message message = new Message();
        final long value = Long.MIN_VALUE;

        final Field field = message.getClass().getDeclaredField("timestamp");
        field.setAccessible(true);
        field.set(message, value);

        long actualValue = message.getTimestamp();

        assertEquals(value, actualValue, "timestamp do not match");
    }

    @Test
    void setTimestamp() throws NoSuchFieldException, IllegalAccessException {
        final Message message = new Message();
        final long value = Long.MIN_VALUE;

        message.setTimestamp(value);

        final Field field = message.getClass().getDeclaredField("timestamp");
        field.setAccessible(true);
        assertEquals(value, field.get(message), "timestamp  do not match");
    }

    @Test
    void testEquals() {
        final Message message = new Message();
        final Message copy = new Message();
        copy.set_id(message.get_id());

        assertEquals(message, copy);
    }

}