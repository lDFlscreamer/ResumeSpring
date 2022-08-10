package com.screamer.resume.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {

    final ResourceLoader resourceLoader;
    @Value("${resume.CV.JAVA.path}")
    private String javaCvRelatedPath;

    public ResourceService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


    public Resource getJavaCV() {
        return getJavaCvResource();
    }

    private Resource getJavaCvResource() {
        return getResource(javaCvRelatedPath);
    }

    private Resource getResource(String path) {
        return resourceLoader.getResource("classpath:".concat(path));
    }

}
