package com.screamer.resume.service.dbServices.resume;

import com.screamer.resume.entity.Resume;
import com.screamer.resume.exceptions.resume.ResumeNotFoundException;
import com.screamer.resume.repository.ResumeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResumeDbServiceImpl implements ResumeDbService {

    private final ResumeRepository resumeRepository;

    public ResumeDbServiceImpl(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    @Override
    public Resume getResumeById(String resumeId) throws ResumeNotFoundException {
        Optional<Resume> foundedResume = resumeRepository.findById(resumeId);
        if (!foundedResume.isPresent()) {
            throw new ResumeNotFoundException(resumeId);
        }

        return foundedResume.get();
    }

    @Override
    public Resume updateResume(Resume resume) {
        return resumeRepository.save(resume);
    }

    @Override
    public Resume updateResumeFile(String resumeId, Object resumeFile) throws ResumeNotFoundException {
        Resume resumeById = this.getResumeById(resumeId);
        resumeById.setResumeFile(resumeFile);
        return updateResume(resumeById);
    }

    @Override
    public void deleteResume(String resumeId) {
        resumeRepository.deleteById(resumeId);
    }
}
