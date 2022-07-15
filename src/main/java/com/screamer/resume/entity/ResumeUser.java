package com.screamer.resume.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResumeUser {
    @Id
    public String id;


    public ResumeUser() {
        this.id = UUID.randomUUID().toString();
    }
}

//{
//        "_id":{"$oid":"62d018d83fd9d50011e5531e"},
//        "connection":"ResumeMongo",
//        "client_id":"SkzVwd3mKMO2uOJCP7ocKv5I6aDCkpec",
//        "email":"999screamer999@gmail.com",
//        "username":"screamer",
//        "password":"$2b$10$Hv/qj4lGboqjAtoucFPQ9eNm.Wb/ukDQvYWpuIF0mBCBz4biQVGgW",
//        "tenant":"dev-rd1axuv2",
//        "transaction":{"id":"YCnLuHLuJmdHYnlL-6MMpc8-jx9VVfZq",
//            "locale":"en",
//            "protocol":"oidc-basic-profile",
//            "requested_scopes":["openid","profile","email"],
//            "acr_values":[],
//            "ui_locales":[],
//            "redirect_uri":"http://localhost:4200",
//            "prompt":[],
//            "state":"dUQ3MC1Vdjg1Nll+S0k1bkVKZ3RYaENhOE5FQU9LczllVlNKWGtIYjBERw==",
//            "login_hint":null,
//            "response_mode":"query",
//            "response_type":["code"]
//        },
//        "request_language":"uk-UA,uk;q=0.9,ru-RU;q=0.8,ru;q=0.7,en-GB;q=0.6,en;q=0.5,en-US;q=0.4",
//        "email_verified":true
//}
