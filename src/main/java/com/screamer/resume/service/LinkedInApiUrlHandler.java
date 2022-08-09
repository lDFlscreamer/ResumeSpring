package com.screamer.resume.service;

import com.screamer.resume.linkedInApi.LinkedInApiAuthorisationTemplate;
import com.screamer.resume.linkedInApi.LinkedInApiScopes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;

import static com.screamer.resume.constant.LinkedInApiConstant.*;
import static com.screamer.resume.linkedInApi.LinkedInApiScopes.*;

@Service
public class LinkedInApiUrlHandler {


    @Value("${linkedin.consumerKey}")
    private String consumerKey;
    @Value("${linkedin.consumerSecret}")
    private String consumerSecret;

    public URI authorizationUrl() {
        LinkedInApiAuthorisationTemplate authorisationTemplate =
                new LinkedInApiAuthorisationTemplate();
        authorisationTemplate
                .setClient_id(this.consumerKey)
                .setRedirect_uri(AUTHORISATION_REDIRECT_URL)
                .setState(STATE)
                .setScopes(NAME_AND_PHOTO);
        return authorisationTemplate.getURl().toUri();
    }

}
