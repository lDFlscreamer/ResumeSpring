package com.screamer.resume.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class AuthController {
    @PostMapping(value ="/user" )
    public void process(@RequestBody Map<String, Object> payload)
            throws Exception {

        System.out.println(payload);
    }
    @GetMapping(value ="/user" )
    public String test() {
        return "test.entrySet()";
    }
}