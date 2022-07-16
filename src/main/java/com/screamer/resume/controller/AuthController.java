package com.screamer.resume.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class AuthController {
    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping(value ="/user" )
    public void process(@RequestBody Map<String, Object> payload)
            throws Exception {
        logger.info(payload.toString());

    }

    @GetMapping(value ="/user" )
    public String test(@RequestHeader Map<String, String> headers) {
        logger.info(headers.toString());
        return null;
    }

    @GetMapping(value ="/GetUser" )
    public String getUser() {
        logger.info("getUser");
        return null;
    }

}