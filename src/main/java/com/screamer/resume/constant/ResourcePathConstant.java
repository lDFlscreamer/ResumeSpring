package com.screamer.resume.constant;

import org.springframework.beans.factory.annotation.Value;

public class ResourcePathConstant {
    @Value("${resume.CV.JAVA.path}")
    public static String javaCvRelatedPath;
}
