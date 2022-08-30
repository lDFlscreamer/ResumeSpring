package com.screamer.resume.controller.restController;

import com.screamer.resume.entity.Resume;
import com.screamer.resume.entity.User;
import com.screamer.resume.exceptions.resume.FileCorruptedException;
import com.screamer.resume.service.businessServices.resume.UserResumeService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("user/resume")
public class UserResumeRestController {

    final UserResumeService userResumeService;

    public UserResumeRestController(UserResumeService userResumeService) {
        this.userResumeService = userResumeService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Resume> getAllUserResume(Authentication authentication) {
        return userResumeService.getAllResume(authentication);
    }

    @PostMapping("/uploadResume")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public User uploadResume(Authentication authentication,
                             @RequestParam("position") String position,
                             @RequestParam("file") MultipartFile file) {
        try {
            return userResumeService.addResumeToUser(authentication, position, file);
        } catch (FileCorruptedException e) {
            throw getFileCorruptedResponse(e);
        }
    }

    @DeleteMapping("/{resumeId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public User deleteResumeFromUser(Authentication authentication,
                                    @PathVariable String resumeId) {
        return userResumeService.removeResumeFromUser(authentication,resumeId);
    }

    private ResponseStatusException getFileCorruptedResponse(FileCorruptedException e) {
        return new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "File Corrupted", e);
    }
}
