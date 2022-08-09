package com.screamer.resume.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import static com.screamer.resume.constant.ResourcePathConstant.javaCvRelatedPath;

@Service
public class ResourceService {

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
