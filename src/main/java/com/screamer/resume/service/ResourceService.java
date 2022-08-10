package com.screamer.resume.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;


@Service
public class ResourceService {

    @Value("${resume.CV.JAVA.path}")
    private String javaCvRelatedPath;

    public Resource getJavaCV() {
        try {
            return getJavaCvResource();
        } catch (FileNotFoundException | MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private Resource getJavaCvResource() throws FileNotFoundException, MalformedURLException {
        File javaCV = getResourceFile(javaCvRelatedPath);
        return getResource(javaCV);
    }

    private Resource getResource(File file) throws MalformedURLException {
        return new UrlResource(file.toPath().toUri());
    }


    private File getResourceFile(String resourcePathFromSourceRoot) throws FileNotFoundException {
        return ResourceUtils.getFile("classpath:".concat(resourcePathFromSourceRoot));
    }


}
