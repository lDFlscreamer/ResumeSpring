package com.screamer.resume.service.dbServices.resume;

import com.screamer.resume.entity.Resume;
import com.screamer.resume.exceptions.resume.ResumeNotFoundException;
import com.screamer.resume.repository.ResumeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResumeDbService {

    private final ResumeRepository resumeRepository;

    public ResumeDbService(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    public Resume getResumeById(String resumeId) throws ResumeNotFoundException {
        Optional<Resume> foundedResume = resumeRepository.findById(resumeId);
        if (!foundedResume.isPresent()) {
            throw new ResumeNotFoundException(resumeId);
        }

        return foundedResume.get();
    }

    public Resume updateResume(Resume resume) {
        return resumeRepository.save(resume);
    }

    public Resume updateResumeFile(String resumeId, Object resumeFile) throws ResumeNotFoundException {
        Resume resumeById = this.getResumeById(resumeId);
        resumeById.setResumeFile(resumeFile);
        return updateResume(resumeById);
    }

    public void deleteResume(String resumeId) {
        resumeRepository.deleteById(resumeId);
    }
}
