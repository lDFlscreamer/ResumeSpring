package com.screamer.resume.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@TestConfiguration
public class JwtDecoderTestConfig {

    @Bean
    @Primary
    JwtDecoder getJwtDecoder() {
        return new JwtDecoder() {
            final String AUTH0_TOKEN = "auth_token";
            final String SUB = "sub";
            final String AUTH0ID = "sms|12345678";

            public Jwt getDummyJwt() {
                // This is a place to add general and maybe custom claims which should be available after parsing token in the live system
                Map<String, Object> claims = new HashMap<>();
                claims.put(SUB, AUTH0ID);

                //This is an object that represents contents of jwt token after parsing
                Map<String, Object> alg = new HashMap<>();
                alg.put("alg", "none");

                return new Jwt(
                        AUTH0_TOKEN,
                        Instant.now(),
                        Instant.now().plusSeconds(30),
                        alg,
                        claims
                );
            }

            @Override
            public Jwt decode(String token) throws JwtException {
                return getDummyJwt();
            }
        };
    }

}
