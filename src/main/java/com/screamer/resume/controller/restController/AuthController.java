package com.screamer.resume.controller.restController;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * This Controller for auth0 purpose
 *
 * Auth0 logIn ,logOut, change user profile and get user endpoints
 */
// TODO: 28.07.2022 change realisation of auth0 methods
@RestController
public class AuthController {
    Logger logger = LoggerFactory.getLogger(AuthController.class);

    /**
     * @param payload auth0 user profile
     * @return  registered user
     */
    // TODO: 28.07.2022 change auth0 signIn to true behavior and interaction with DB
    @PostMapping(
            value = "/user",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> signIn(@RequestBody Map<String, Object> payload) {
        logger.info(payload.toString());
        return payload;
    }


    /**
     * @param headers  parameter of user ( like email and pass)
     * @return depends on auth0 behavior
     */
    // TODO: 28.07.2022 change auth0 logIn to true behavior and interaction with DB
    @GetMapping(value = "/user")
    @ResponseStatus(HttpStatus.CREATED)
    public String logIn(@RequestBody Map<String, String> headers) {
        logger.info(headers.toString());
        return null;
    }

    /**
     *
     * @return user profile from DB
     */
    // TODO: 28.07.2022 change auth0 GetUser to true behavior and interaction with DB
    @GetMapping(value = "/GetUser",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> getUser() {
        logger.info("getUser");
        Map<String, String> user = new HashMap<>();
        user.put("user_id", "test");
        user.put("nickname", "test");
        user.put("email", "test");
        return user;
    }

}