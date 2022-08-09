package com.screamer.resume.socialIntegration.linkedIn.ApiTemplate;

import com.screamer.resume.socialIntegration.linkedIn.apiScopes.LinkedInApiScopes;
import com.screamer.resume.socialIntegration.linkedIn.apiScopes.LinkedInApiScopesEncoder;

public class LinkedInApiAuthorisationTemplate extends LinkedInApiHttpTemplate {


    public LinkedInApiAuthorisationTemplate() {
        super("https://www.linkedin.com/oauth/v2/authorization?response_type=code&client_id={client_id}&redirect_uri={redirect_uri}&state={state}&scope={scope}");
    }

    public void setClient_id(String client_id) {
        this.parameters.put("client_id", client_id);
    }

    public void setRedirect_uri(String redirect_uri) {
        this.parameters.put("redirect_uri", redirect_uri);
    }

    public void setState(String state) {
        this.parameters.put("state", state);
    }

    public void changeScopes(LinkedInApiScopes... scopes) {
        String encodedScopes = LinkedInApiScopesEncoder.encodeApiScope(scopes);
        setScopes(encodedScopes);
    }

    public void setScopes(String encodedScopes) {
        this.parameters.put("scope", encodedScopes);
    }

    public void appendScopes(LinkedInApiScopes... newScopes) {
        String newEncodedScopes = LinkedInApiScopesEncoder.encodeApiScope(newScopes);

        String scopes = (String) this.parameters.getOrDefault("scope", "");
        if (!scopes.isEmpty()) {
            newEncodedScopes = LinkedInApiScopesEncoder.concatenatePermissionCodes(scopes, newEncodedScopes);
        }
        setScopes(newEncodedScopes);
    }


}
