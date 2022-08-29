package com.screamer.resume.service.resume;

import com.screamer.resume.entity.Resume;
import com.screamer.resume.entity.User;
import com.screamer.resume.service.dbService.user.UserDbService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class ResumeService {

    private final UserDbService userDbService;
    private final ResumeBase64Encoder encoder;

    public ResumeService(UserDbService userDbService, ResumeBase64Encoder encoder) {
        this.userDbService = userDbService;
        this.encoder = encoder;
    }


    public User addResumeToUser(Authentication authentication, String position, byte[] file) {
        User user = userDbService.getOrCreate(authentication.getName());
        Resume resume = buildResume(position, file);
        user.addResume(resume);
        return userDbService.updateUser(user);
    }



    public User removeResumeToUser(Resume resume, User user) {
        user.removeResume(resume);
        return userDbService.updateUser(user);
    }


    private Resume buildResume(String position, byte[] file) {
        Resume resume = new Resume();
        resume.setPosition(position);
        Object encodedResumeFile = encoder.encode(file);
        resume.setResumeFile(encodedResumeFile);
        return resume;
    }


}
