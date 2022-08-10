package com.screamer.resume.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;


@Service
public class ResourceService {

    @Value("${resume.CV.JAVA.path}")
    private String javaCvRelatedPath;

    public Resource getJavaCV() {
        return getJavaCvResource();
    }

    private Resource getJavaCvResource() {
        URL javCvUrl = getResourceURL(javaCvRelatedPath);
        return getResource(javCvUrl);
    }

    private Resource getResource(URL resourceUrl) {
        return new UrlResource(resourceUrl);
    }

    private URL getResourceURL(String path){
        return getClass().getResource(path);
    }

}
