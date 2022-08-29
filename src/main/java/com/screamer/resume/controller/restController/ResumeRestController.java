package com.screamer.resume.controller.restController;

import com.screamer.resume.entity.User;
import com.screamer.resume.service.resume.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("user")
public class ResumeRestController {

    final
    ResumeService resumeService;

    public ResumeRestController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }


    @PostMapping("/uploadResume")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public User uploadResume(Authentication authentication,
                             @RequestParam("position") String position,
                             @RequestParam("file") MultipartFile file) throws IOException {
        return resumeService.addResumeToUser(authentication, position, file.getBytes());
    }
}
