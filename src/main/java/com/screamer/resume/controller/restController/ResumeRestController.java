package com.screamer.resume.controller.restController;

import com.screamer.resume.entity.Resume;
import com.screamer.resume.exceptions.resume.FileCorruptedException;
import com.screamer.resume.exceptions.resume.ResumeNotFoundException;
import com.screamer.resume.service.businessServices.resume.ResumeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("resume")
public class ResumeRestController {


    private final ResumeService resumeService;

    public ResumeRestController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @GetMapping("/{resumeId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Resume getResume(@PathVariable String resumeId) {
        try {
            return resumeService.getResume(resumeId);
        } catch (ResumeNotFoundException e) {
            throw getResumeNotFoundResponse(e);
        }
    }

    @PostMapping("/{resumeId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Resume updateResume(@PathVariable String resumeId,
                               @RequestParam("position") String position,
                               @RequestParam("file") MultipartFile file) {
        try {
            return resumeService.updateResume(resumeId, position, file);
        } catch (FileCorruptedException e) {
            throw getFileCorruptedExceptionResponse(e);
        }
    }

    private ResponseStatusException getResumeNotFoundResponse(ResumeNotFoundException e) {
        return new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Resume Not Found", e);
    }

    private ResponseStatusException getFileCorruptedExceptionResponse(FileCorruptedException e) {
        return new ResponseStatusException(
                HttpStatus.NOT_ACCEPTABLE, "file corrupted", e);
    }
}
