package com.screamer.resume.socialIntegration.linkedIn.apiScopes;

import java.util.Arrays;
import java.util.stream.Stream;

import static com.screamer.resume.constant.LinkedInApiConstant.SCOPE_DELIMITER;

public class LinkedInApiScopesEncoder {

    public static String concatenatePermissionCodes(String firstCodes, String secondCodes) {
        return firstCodes.concat(SCOPE_DELIMITER).concat(secondCodes);
    }

    public static String encodeApiScope(LinkedInApiScopes... scopes) {
        Stream<String> permissionCodes = Arrays.stream(scopes).map(LinkedInApiScopes::getPermissionCode);
        String encodedScopes = permissionCodes
                .reduce(LinkedInApiScopesEncoder::concatenatePermissionCodes)
                .orElse("");
        return encodedScopes;
    }
}
