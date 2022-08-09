package com.screamer.resume.controller.restController;

import com.screamer.resume.service.LinkedInApiUrlHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LinkedInRestController {

    @Autowired
    LinkedInApiUrlHandler linkedInApiUrlHandler;

    @GetMapping(value = "/connect/linkedIn")
    @ResponseStatus(HttpStatus.CREATED)
    public String getAuthorisationLink() {
        return linkedInApiUrlHandler.getAuthorizationUrl().toString();
    }
}