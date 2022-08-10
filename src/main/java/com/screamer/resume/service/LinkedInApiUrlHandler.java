package com.screamer.resume.service;

import com.screamer.resume.socialIntegration.linkedIn.LinkedInApiAuthorisationTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;

import static com.screamer.resume.constant.LinkedInApiConstant.AUTHORISATION_REDIRECT_URL;
import static com.screamer.resume.constant.LinkedInApiConstant.STATE;
import static com.screamer.resume.socialIntegration.linkedIn.LinkedInApiScopes.NAME_AND_PHOTO;

@Service
public class LinkedInApiUrlHandler {


    @Value("${linkedin.consumerKey}")
    private String consumerKey;
    @Value("${linkedin.consumerSecret}")
    private String consumerSecret;

    public URI getAuthorizationUrl() {
        LinkedInApiAuthorisationTemplate authTemplate =
                new LinkedInApiAuthorisationTemplate();

        authTemplate.setClient_id(this.consumerKey);
        authTemplate.setRedirect_uri(AUTHORISATION_REDIRECT_URL);
        authTemplate.setState(STATE);
        authTemplate.changeScopes(NAME_AND_PHOTO);
        return authTemplate.getURl().toUri();
    }

}
