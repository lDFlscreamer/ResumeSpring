package com.screamer.resume.service.businessServices.resume;

import com.screamer.resume.entity.Resume;
import com.screamer.resume.exceptions.resume.ResumeNotFoundException;
import org.springframework.web.multipart.MultipartFile;

public interface ResumeService {
    Resume getResume(String resumeId) throws ResumeNotFoundException;

    Resume updateResume(String resumeId, String position, MultipartFile file) throws ResumeNotFoundException;
}
