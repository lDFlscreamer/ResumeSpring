package com.screamer.resume.service.businessServices.resume;

import com.screamer.resume.entity.Resume;
import com.screamer.resume.entity.User;
import com.screamer.resume.exceptions.resume.FileCorruptedException;
import com.screamer.resume.service.dbServices.resume.ResumeDbService;
import com.screamer.resume.service.dbServices.user.UserDbService;
import com.screamer.resume.utils.ResumeEncoder;
import com.screamer.resume.utils.ResumeFabric;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class UserResumeService {

    private final UserDbService userDbService;
    private final ResumeFabric resumeFabric;

    public UserResumeService(UserDbService userDbService, ResumeEncoder encoder, ResumeDbService resumeDbService, ResumeService resumeService, ResumeFabric resumeFabric) {
        this.userDbService = userDbService;
        this.resumeFabric = resumeFabric;
    }

    public List<Resume> getAllResume(Authentication authentication) {
        User user = userDbService.getOrCreate(authentication.getName());
        return user.getResumeList();
    }

    public User addResumeToUser(Authentication authentication, String position, MultipartFile file) throws FileCorruptedException {
        try {
            return addResume(authentication, position, file);
        } catch (IOException e) {
            throw new FileCorruptedException(file, e);
        }
    }

    private User addResume(Authentication authentication, String position, MultipartFile file) throws IOException {
        User user = userDbService.getOrCreate(authentication.getName());
        Resume resume = resumeFabric.buildResume(position, file);
        user.addResume(resume);
        return userDbService.updateUser(user);
    }

    public User removeResumeFromUser(Authentication authentication, String resumeId) {
            return removeResume(authentication, resumeId);
    }

    private User removeResume(Authentication authentication, String resumeId) {
        User user = userDbService.getOrCreate(authentication.getName());
        user.getResumeList().removeIf(r->r.get_id().equals(resumeId));
        return userDbService.updateUser(user);
    }


}
