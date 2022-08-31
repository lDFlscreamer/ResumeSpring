package com.screamer.resume.entity;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class ResumeTest {

    @Test
    void get_id() throws IllegalAccessException, NoSuchFieldException {
        final Resume resume = new Resume();
        final String value = UUID.randomUUID().toString();

        final Field field = resume.getClass().getDeclaredField("_id");
        field.setAccessible(true);
        field.set(resume, value);

        String actualValue = resume.get_id();

        assertEquals(value, actualValue, "id do not match");
    }

    @Test
    void set_id() throws IllegalAccessException, NoSuchFieldException {
        final Resume resume = new Resume();
        final String value = UUID.randomUUID().toString();

        resume.set_id(value);

        final Field field = resume.getClass().getDeclaredField("_id");
        field.setAccessible(true);
        assertEquals(value, field.get(resume), "_id  do not match");
    }

    @Test
    void getResumeFileType() throws IllegalAccessException, NoSuchFieldException {
        final Resume resume = new Resume();
        final ResumeFileType value = ResumeFileType.PDF;

        final Field field = resume.getClass().getDeclaredField("resumeFileType");
        field.setAccessible(true);
        field.set(resume, value);

        ResumeFileType actualValue = resume.getResumeFileType();

        assertEquals(value, actualValue, "resumeFileType do not match");
    }

    @Test
    void setResumeFileType() throws IllegalAccessException, NoSuchFieldException {
        final Resume resume = new Resume();
        final ResumeFileType value = ResumeFileType.PDF;

        resume.setResumeFileType(value);

        final Field field = resume.getClass().getDeclaredField("resumeFileType");
        field.setAccessible(true);
        assertEquals(value, field.get(resume), "resumeFileType  do not match");
    }

    @Test
    void getPosition() throws IllegalAccessException, NoSuchFieldException {
        final Resume resume = new Resume();
        final String value = "position";

        final Field field = resume.getClass().getDeclaredField("position");
        field.setAccessible(true);
        field.set(resume, value);

        String actualValue = resume.getPosition();

        assertEquals(value, actualValue, "position do not match");
    }

    @Test
    void setPosition() throws IllegalAccessException, NoSuchFieldException {
        final Resume resume = new Resume();
        final String value = "position";

        resume.setPosition(value);

        final Field field = resume.getClass().getDeclaredField("position");
        field.setAccessible(true);
        assertEquals(value, field.get(resume), "position  do not match");
    }

    @Test
    void getResumeFileName() throws IllegalAccessException, NoSuchFieldException {
        final Resume resume = new Resume();
        final String value = "resumeFileName";

        final Field field = resume.getClass().getDeclaredField("resumeFileName");
        field.setAccessible(true);
        field.set(resume, value);

        String actualValue = resume.getResumeFileName();

        assertEquals(value, actualValue, "resumeFileName do not match");
    }

    @Test
    void setResumeFileName() throws IllegalAccessException, NoSuchFieldException {
        final Resume resume = new Resume();
        final String value = "resumeFileName";

        resume.setResumeFileName(value);

        final Field field = resume.getClass().getDeclaredField("resumeFileName");
        field.setAccessible(true);
        assertEquals(value, field.get(resume), "resumeFileName  do not match");
    }

    @Test
    void getResumeFile() throws IllegalAccessException, NoSuchFieldException {
        final Resume resume = new Resume();
        final Object value = mock(Object.class);

        final Field field = resume.getClass().getDeclaredField("resumeFile");
        field.setAccessible(true);
        field.set(resume, value);

        Object actualValue = resume.getResumeFile();

        assertEquals(value, actualValue, "resumeFile do not match");
    }

    @Test
    void setResumeFile() throws IllegalAccessException, NoSuchFieldException {
        final Resume resume = new Resume();
        final Object value = mock(Object.class);

        resume.setResumeFile(value);

        final Field field = resume.getClass().getDeclaredField("resumeFile");
        field.setAccessible(true);
        assertEquals(value, field.get(resume), "resumeFile  do not match");
    }

}