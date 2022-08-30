package com.screamer.resume.utils;

import com.screamer.resume.entity.Resume;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ResumeFabric {

    private final ResumeEncoder encoder;

    public ResumeFabric(ResumeEncoder encoder) {
        this.encoder = encoder;
    }

    public Resume buildResume(String position, MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        return getResumeInstance(position, fileName, file.getBytes());
    }

    public Resume buildResume(String resumeId, String position, MultipartFile file) throws IOException {
        Resume resume = buildResume(position, file);
        resume.set_id(resumeId);
        return resume;
    }

    private Resume getResumeInstance(String position, String fileName, byte[] file) {
        Resume resume = new Resume();
        resume.setPosition(position);
        resume.setResumeFileName(fileName);
        Object encodedResumeFile = encoder.encode(file);
        resume.setResumeFile(encodedResumeFile);
        return resume;
    }
}
