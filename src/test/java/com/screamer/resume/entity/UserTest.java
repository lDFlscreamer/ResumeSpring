package com.screamer.resume.entity;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UserTest {

    @Test
    void get_id() throws NoSuchFieldException, IllegalAccessException {
        final User user = new User();
        final String value = UUID.randomUUID().toString();

        final Field field = user.getClass().getDeclaredField("_id");
        field.setAccessible(true);
        field.set(user, value);

        String actualValue = user.get_id();

        assertEquals(value, actualValue, "id do not match");
    }

    @Test
    void set_id() throws NoSuchFieldException, IllegalAccessException {
        final User user = new User();
        final String value = UUID.randomUUID().toString();

        user.set_id(value);

        final Field field = user.getClass().getDeclaredField("_id");
        field.setAccessible(true);
        assertEquals(value, field.get(user), "_id  do not match");
    }

    @Test
    void getUserAuthId() throws NoSuchFieldException, IllegalAccessException {
        final User user = new User();
        final String value = UUID.randomUUID().toString();

        final Field field = user.getClass().getDeclaredField("userAuthId");
        field.setAccessible(true);
        field.set(user, value);

        String actualValue = user.getUserAuthId();

        assertEquals(value, actualValue, "userAuthId do not match");
    }

    @Test
    void setUserAuthId() throws NoSuchFieldException, IllegalAccessException {
        final User user = new User();
        final String value = UUID.randomUUID().toString();

        user.setUserAuthId(value);

        final Field field = user.getClass().getDeclaredField("userAuthId");
        field.setAccessible(true);
        assertEquals(value, field.get(user), "userAuthId  do not match");
    }

    @Test
    void getResumeList() throws NoSuchFieldException, IllegalAccessException {
        final User user = new User();
        final List<Resume> value = new ArrayList<>();

        final Field field = user.getClass().getDeclaredField("resumeList");
        field.setAccessible(true);
        field.set(user, value);

        List<Resume> actualValue = user.getResumeList();

        assertEquals(value, actualValue, "resumeList do not match");
    }

    @Test
    void setResumeList() throws NoSuchFieldException, IllegalAccessException {
        final User user = new User();
        final List<Resume> value = new ArrayList<>();

        user.setResumeList(value);

        final Field field = user.getClass().getDeclaredField("resumeList");
        field.setAccessible(true);
        assertEquals(value, field.get(user), "resumeList  do not match");
    }

    @Test
    void addResume() throws NoSuchFieldException, IllegalAccessException {
        final User user = new User();
        final List<Resume> value = new ArrayList<>();
        Resume mockResume=mock(Resume.class);
        final Field field = user.getClass().getDeclaredField("resumeList");
        field.setAccessible(true);
        field.set(user, value);

        user.addResume(mockResume);

        assertTrue(value.contains(mockResume), "Resume do not added");
    }

    @Test
    void removeResume() throws NoSuchFieldException, IllegalAccessException {
        final User user = new User();
        final List<Resume> value = new ArrayList<>();
        Resume mockResume=mock(Resume.class);
        value.add(mockResume);
        final Field field = user.getClass().getDeclaredField("resumeList");
        field.setAccessible(true);
        field.set(user, value);

        user.removeResume(mockResume);

        assertFalse(value.contains(mockResume), "Resume do not removed");
    }
}