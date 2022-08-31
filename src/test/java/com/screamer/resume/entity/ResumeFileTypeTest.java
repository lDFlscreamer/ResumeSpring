package com.screamer.resume.entity;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResumeFileTypeTest {

    @Test
    void getCode() throws NoSuchFieldException, IllegalAccessException {
        final ResumeFileType fileType = ResumeFileType.PDF;

        final Field field = fileType.getClass().getDeclaredField("typeCode");
        field.setAccessible(true);


        final String actualCode = fileType.getCode();

        assertEquals(field.get(fileType), actualCode, "typeCode do not match");
    }
}