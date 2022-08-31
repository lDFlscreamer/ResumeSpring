package com.screamer.resume.utils;

import com.screamer.resume.entity.Resume;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith({SpringExtension.class})
class ResumeFabricTest {

    @Mock
    private ResumeEncoder encoder;
    @InjectMocks
    private ResumeFabric resumeFabric;


    @Test
    void buildResume() throws IOException {
        String position = "any";
        MultipartFile file = mock(MultipartFile.class);
        String fileName = "fileName";
        byte[] fileBytes = new byte[3];
        Object encodedFile = mock(Object.class);

        when(file.getName()).thenReturn(fileName);
        when(file.getBytes()).thenReturn(fileBytes);
        when(encoder.encode(fileBytes)).thenReturn(encodedFile);

        Resume resume = resumeFabric.buildResume(position, file);

        verify(encoder, atLeast(1)).encode(any());
        assertEquals(position, resume.getPosition(), "Resume position do not match");
        assertEquals(encodedFile, resume.getResumeFile(), "Resume encoded File do not match");
    }

    @Test
    void buildResume_withResumeId() throws IOException {
        String resumeId = UUID.randomUUID().toString();
        String position = "any";
        MultipartFile file = mock(MultipartFile.class);
        String fileName = "fileName";
        byte[] fileBytes = new byte[3];
        Object encodedFile = mock(Object.class);

        when(file.getName()).thenReturn(fileName);
        when(file.getBytes()).thenReturn(fileBytes);
        when(encoder.encode(fileBytes)).thenReturn(encodedFile);

        Resume resume = resumeFabric.buildResume(resumeId, position, file);

        verify(encoder, atLeast(1)).encode(any());
        assertEquals(resumeId, resume.get_id(), "Resume id do not match");
        assertEquals(position, resume.getPosition(), "Resume position do not match");
        assertEquals(encodedFile, resume.getResumeFile(), "Resume encoded File do not match");
    }
}