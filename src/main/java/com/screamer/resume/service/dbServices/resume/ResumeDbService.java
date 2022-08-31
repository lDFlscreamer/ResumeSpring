package com.screamer.resume.service.dbServices.resume;

import com.screamer.resume.entity.Resume;
import com.screamer.resume.exceptions.resume.ResumeNotFoundException;

public interface ResumeDbService {
    Resume getResumeById(String resumeId) throws ResumeNotFoundException;

    Resume updateResume(Resume resume);

    Resume updateResumeFile(String resumeId, Object resumeFile) throws ResumeNotFoundException;

    void deleteResume(String resumeId);
}
