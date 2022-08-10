package com.screamer.resume.socialIntegration.linkedIn.apiScopes;

public enum LinkedInApiScopes {
    FULL_PROFILE("r_fullprofile"),
    EMAIL_ADDRESS("r_emailaddress"),
    NAME_AND_PHOTO("r_liteprofile");



    private final String permissionCode;

    LinkedInApiScopes(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public String getPermissionCode() {
        return permissionCode;
    }
}
