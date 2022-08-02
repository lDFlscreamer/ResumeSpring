package com.screamer.resume.linkedInApi;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

public class LinkedInApiHttpTemplate {
    protected String httpString;
    protected Map<String,Object> parameters;


    public LinkedInApiHttpTemplate(String httpString) {
        this();
        this.httpString = httpString;
    }


    public LinkedInApiHttpTemplate() {
        this.parameters= new HashMap<>();
    }

    public UriComponents getURl(){
        return UriComponentsBuilder
                .fromHttpUrl(this.httpString)
                .buildAndExpand(this.parameters);
    }

}
