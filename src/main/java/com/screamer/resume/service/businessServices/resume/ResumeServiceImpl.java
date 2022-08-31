package com.screamer.resume.service.businessServices.resume;

import com.screamer.resume.entity.Resume;
import com.screamer.resume.exceptions.resume.ResumeNotFoundException;
import com.screamer.resume.service.dbServices.resume.ResumeDbService;
import com.screamer.resume.utils.ResumeFabric;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ResumeServiceImpl implements ResumeService {

    private final ResumeDbService resumeDbService;
    private final ResumeFabric resumeFabric;

    public ResumeServiceImpl(ResumeDbService resumeDbService, ResumeFabric resumeFabric) {
        this.resumeDbService = resumeDbService;
        this.resumeFabric = resumeFabric;
    }

    @Override
    public Resume getResume(String resumeId) throws ResumeNotFoundException {
        return resumeDbService.getResumeById(resumeId);
    }

    @Override
    public Resume updateResume(String resumeId, String position, MultipartFile file) throws ResumeNotFoundException {
        try {
            Resume resume = resumeFabric.buildResume(resumeId, position, file);
            return resumeDbService.updateResume(resume);
        } catch (IOException e) {
            throw new ResumeNotFoundException(resumeId);
        }
    }
}
