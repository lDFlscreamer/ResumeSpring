package com.screamer.resume.service.businessServices.resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl implements ResourceService {

    final ResourceLoader resourceLoader;
    @Value("${resume.CV.JAVA.path}")
    private String javaCvRelatedPath;

    public ResourceServiceImpl(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
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
