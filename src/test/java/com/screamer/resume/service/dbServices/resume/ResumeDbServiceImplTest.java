package com.screamer.resume.service.dbServices.resume;

import com.screamer.resume.entity.Resume;
import com.screamer.resume.exceptions.resume.ResumeNotFoundException;
import com.screamer.resume.repository.ResumeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith({SpringExtension.class})
class ResumeDbServiceImplTest {
    @Mock
    private ResumeRepository resumeRepository;
    @InjectMocks
    private ResumeDbServiceImpl resumeDbService;

    @Test
    void getResumeById() throws ResumeNotFoundException {
        String resumeId = UUID.randomUUID().toString();
        Resume mockResume = mock(Resume.class);

        when(resumeRepository.findById(resumeId)).thenReturn(Optional.of(mockResume));

        Resume resume = resumeDbService.getResumeById(resumeId);

        verify(resumeRepository, times(1)).findById(resumeId);
        assertEquals(mockResume, resume, "Resume do not match");
    }

    @Test
    void getResumeById_ResumeNotFound() {
        String resumeId = UUID.randomUUID().toString();

        when(resumeRepository.findById(resumeId)).thenReturn(Optional.empty());

        Executable executable = () -> resumeDbService.getResumeById(resumeId);
        ResumeNotFoundException exception = assertThrows(ResumeNotFoundException.class, executable);

        verify(resumeRepository, times(1)).findById(resumeId);
        assertEquals(resumeId, exception.getResumeId(), "Resume id do not match");
    }

    @Test
    void updateResume() {
        Resume mockResume = mock(Resume.class);

        when(resumeRepository.save(mockResume)).thenReturn(mockResume);

        Resume resume = resumeDbService.updateResume(mockResume);

        verify(resumeRepository, times(1)).save(mockResume);
        assertEquals(mockResume, resume, "Resume do not match");
    }

    @Test
    void updateResumeFile() throws ResumeNotFoundException {
        String resumeId = UUID.randomUUID().toString();
        Object resumeFile = mock(Object.class);
        Resume mockResume = new Resume();

        when(resumeRepository.findById(resumeId)).thenReturn(Optional.of(mockResume));
        when(resumeRepository.save(mockResume)).thenReturn(mockResume);

        Resume resume = resumeDbService.updateResumeFile(resumeId, resumeFile);

        verify(resumeRepository, times(1)).findById(resumeId);
        verify(resumeRepository, times(1)).save(mockResume);
        assertEquals(resumeFile, resume.getResumeFile(), "Resume file do not match");
    }

    @Test
    void updateResumeFile_ResumeNotFound() {
        String resumeId = UUID.randomUUID().toString();
        Object resumeFile = mock(Object.class);

        when(resumeRepository.findById(resumeId)).thenReturn(Optional.empty());

        Executable executable = () -> resumeDbService.updateResumeFile(resumeId, resumeFile);
        ResumeNotFoundException exception = assertThrows(ResumeNotFoundException.class, executable);


        verify(resumeRepository, times(1)).findById(resumeId);
        verify(resumeRepository, times(0)).save(any(Resume.class));
        assertEquals(resumeId, exception.getResumeId(), "Resume id do not match");
    }

    @Test
    void deleteResume() {
        String resumeId = UUID.randomUUID().toString();

        resumeDbService.deleteResume(resumeId);

        verify(resumeRepository, times(1)).deleteById(resumeId);
        verify(resumeRepository, times(1)).deleteById(anyString());
    }
}