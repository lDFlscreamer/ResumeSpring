package com.screamer.resume.service.businessServices.resume;

import com.screamer.resume.entity.Resume;
import com.screamer.resume.exceptions.resume.FileCorruptedException;
import com.screamer.resume.exceptions.resume.ResumeNotFoundException;
import com.screamer.resume.service.dbServices.resume.ResumeDbServiceImpl;
import com.screamer.resume.utils.ResumeFabric;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith({SpringExtension.class})
class ResumeServiceImplTest {
    @Mock
    ResumeDbServiceImpl resumeDbService;
    @Mock
    ResumeFabric resumeFabric;

    @InjectMocks
    ResumeServiceImpl resumeService;

    @Test
    void getResume() throws ResumeNotFoundException {
        Resume mockResume = mock(Resume.class);
        String resumeId = UUID.randomUUID().toString();

        when(resumeDbService.getResumeById(resumeId)).thenReturn(mockResume);

        Resume resume = resumeService.getResume(resumeId);

        verify(resumeDbService, times(1)).getResumeById(resumeId);
        assertEquals(mockResume, resume, "Resume do not match");
    }

    @Test
    void getResume_ResumeNotFound() throws ResumeNotFoundException {
        String resumeId = UUID.randomUUID().toString();

        ResumeNotFoundException exception = new ResumeNotFoundException(resumeId);
        when(resumeDbService.getResumeById(resumeId)).thenThrow(exception);

        Executable executable = () -> resumeService.getResume(resumeId);
        ResumeNotFoundException notFoundException = assertThrows(ResumeNotFoundException.class, executable);

        verify(resumeDbService, times(1)).getResumeById(resumeId);
        assertEquals(resumeId, notFoundException.getResumeId(), "Resume id do not match");
    }


    @Test
    void updateResume() throws IOException, FileCorruptedException {
        String resumeId = UUID.randomUUID().toString();
        String position = "any";
        MultipartFile mockFile = mock(MultipartFile.class);
        Resume mockResume = mock(Resume.class);

        when(resumeFabric.buildResume(resumeId, position, mockFile)).thenReturn(mockResume);
        when(resumeDbService.updateResume(any(Resume.class))).thenAnswer(r -> r.getArguments()[0]);

        Resume resume = resumeService.updateResume(resumeId, position, mockFile);

        verify(resumeFabric, times(1)).buildResume(resumeId, position, mockFile);
        verify(resumeDbService, times(1)).updateResume(mockResume);
        assertEquals(mockResume, resume, "Resume do not match");
    }

    @Test
    void updateResume_FileCorrupted() throws IOException {
        String resumeId = UUID.randomUUID().toString();
        String position = "any";
        MultipartFile mockFile = mock(MultipartFile.class);

        when(resumeFabric.buildResume(resumeId, position, mockFile)).thenThrow(new IOException());

        Executable executable = () -> resumeService.updateResume(resumeId, position, mockFile);
        FileCorruptedException exception = assertThrows(FileCorruptedException.class, executable);

        verify(resumeFabric, times(1)).buildResume(resumeId, position, mockFile);
        verify(resumeDbService, times(0)).updateResume(any(Resume.class));
        assertEquals(mockFile, exception.getCorruptedFile(), "file do not match");
    }
}