package com.screamer.resume.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {
    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping(
            value = "/user",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> process(@RequestBody Map<String, Object> payload)
            throws Exception {
        logger.info(payload.toString());
        return payload;
    }

    @GetMapping(value = "/user")
    @ResponseStatus( HttpStatus.CREATED)
    public String test(@RequestBody Map<String, String> headers) {
        logger.info(headers.toString());
        return null;
    }

    @GetMapping(value = "/GetUser",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> getUser() {
        logger.info("getUser");
        Map<String, String> user = new HashMap<>();
        user.put("user_id","test");
        user.put("nickname","test");
        user.put("email","test");
        return user;
    }

}