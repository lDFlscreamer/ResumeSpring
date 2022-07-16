package com.screamer.resume.controller;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "api/public", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {
    @RequestMapping(
            value = "/user",
            method = RequestMethod.POST)
    public void process(@RequestBody Map<String, Object> payload)
            throws Exception {

        System.out.println(payload);
    }
}