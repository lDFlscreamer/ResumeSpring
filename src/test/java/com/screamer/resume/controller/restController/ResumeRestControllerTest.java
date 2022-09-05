package com.screamer.resume.controller.restController;

import com.screamer.resume.entity.Resume;
import com.screamer.resume.exceptions.resume.FileCorruptedException;
import com.screamer.resume.exceptions.resume.ResumeNotFoundException;
import com.screamer.resume.service.businessServices.resume.ResumeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith({SpringExtension.class})
class ResumeRestControllerTest {
    @Mock
    private ResumeService resumeService;

    @InjectMocks
    private ResumeRestController controller;

    @Test
    void getResume() throws ResumeNotFoundException {
        String resumeId = UUID.randomUUID().toString();
        Resume mockResume = new Resume();

        when(resumeService.getResume(resumeId)).thenReturn(mockResume);

        Resume resume = controller.getResume(resumeId);

        verify(resumeService, times(1)).getResume(resumeId);
        assertEquals(mockResume.get_id(), resume.get_id(), "Resume id do not match");
    }

    @Test
    void getResume_ResumeNotFound() throws ResumeNotFoundException {
        String resumeId = UUID.randomUUID().toString();
        ResumeNotFoundException notFoundException = new ResumeNotFoundException(resumeId);

        when(resumeService.getResume(resumeId)).thenThrow(notFoundException);

        Executable executable = () -> controller.getResume(resumeId);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, executable);

        verify(resumeService, times(1)).getResume(resumeId);
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus(), "Response status do not match");
        assertEquals(notFoundException, exception.getCause(), "Cause do not match");
    }

    @Test
    void updateResume() throws FileCorruptedException {
        String resumeId = UUID.randomUUID().toString();
        String position = "testPosition";
        String fileName = "fileName";
        MockMultipartFile file = new MockMultipartFile(
                "file",
                fileName,
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );
        Resume mockResume = mock(Resume.class);

        when(resumeService.updateResume(resumeId, position, file)).thenReturn(mockResume);

        Resume resume = controller.updateResume(resumeId, position, file);

        verify(resumeService, times(1)).updateResume(resumeId, position, file);
        assertEquals(mockResume, resume, "Resume do not match");
    }

    @Test
    void updateResume_FileCorrupted() throws FileCorruptedException {
        String resumeId = UUID.randomUUID().toString();
        String position = "testPosition";
        String fileName = "fileName";
        MockMultipartFile file = new MockMultipartFile(
                "file",
                fileName,
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );
        FileCorruptedException fileCorruptedException = new FileCorruptedException(file, new IOException());

        when(resumeService.updateResume(resumeId, position, file))
                .thenThrow(fileCorruptedException);

        Executable executable = () -> controller.updateResume(resumeId, position, file);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, executable);

        verify(resumeService, times(1)).updateResume(resumeId, position, file);
        assertEquals(HttpStatus.NOT_ACCEPTABLE, exception.getStatus(), "Response status do not match");
        assertEquals(fileCorruptedException, exception.getCause(), "Cause do not match");
    }
}