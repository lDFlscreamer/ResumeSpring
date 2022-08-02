package com.screamer.resume.linkedInApi;

import java.util.Arrays;

import static com.screamer.resume.constant.LinkedInApiConstant.SCOPE_DELIMITER;

public class LinkedInApiAuthorisationTemplate extends LinkedInApiHttpTemplate {


    public LinkedInApiAuthorisationTemplate() {
        super("https://www.linkedin.com/oauth/v2/authorization?response_type=code&client_id={client_id}&redirect_uri={redirect_uri}&state={state}&scope={scope}");
    }

    public LinkedInApiAuthorisationTemplate setClient_id(String client_id) {
        this.parameters.put("client_id", client_id);

        return this;
    }

    public LinkedInApiAuthorisationTemplate setRedirect_uri(String redirect_uri) {
        this.parameters.put("redirect_uri", redirect_uri);
        return this;
    }

    public LinkedInApiAuthorisationTemplate setState(String state) {
        this.parameters.put("state", state);
        return this;
    }

    public LinkedInApiAuthorisationTemplate setScopes(LinkedInApiScopes... scopes) {
        String scopesEncoded = Arrays.stream(scopes).map(LinkedInApiScopes::getPermissionCode)
                .reduce((s, scope2) -> s.concat("%20").concat(scope2)).orElse("");
        this.parameters.put("scope", scopesEncoded);
        return this;
    }

    public LinkedInApiAuthorisationTemplate appendScope(LinkedInApiScopes... scopes) {
        String scopesEncoded = Arrays.stream(scopes).map(LinkedInApiScopes::getPermissionCode)
                .reduce((s, scope2) -> s.concat(SCOPE_DELIMITER).concat(scope2)).orElse("");

        String scope = (String) this.parameters.getOrDefault("scope", "");
        if (!scope.isEmpty()) {
            scopesEncoded = scope.concat(SCOPE_DELIMITER).concat(scopesEncoded);
        }
        this.parameters.put("scope", scopesEncoded);
        return this;
    }


}
