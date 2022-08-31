package com.screamer.resume.service.businessServices.resume;

import com.screamer.resume.entity.Resume;
import com.screamer.resume.entity.User;
import com.screamer.resume.exceptions.resume.FileCorruptedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserResumeService {
    List<Resume> getAllResume(Authentication authentication);

    User addResumeToUser(Authentication authentication, String position, MultipartFile file) throws FileCorruptedException;

    User removeResumeFromUser(Authentication authentication, String resumeId);
}
