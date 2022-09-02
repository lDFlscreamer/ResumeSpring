package com.screamer.resume.controller.restController;

import com.screamer.resume.service.businessServices.resource.ResourceService;
import com.screamer.resume.service.businessServices.resource.ResourceServiceImpl;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "resource/CV")
public class ResourceCvRestController {


    private final ResourceService resourceService;

    public ResourceCvRestController(ResourceServiceImpl resourceService) {
        this.resourceService = resourceService;
    }

    @GetMapping("/Java")
    public ResponseEntity<?> getJavaCv() {
        Resource javaCV = resourceService.getJavaCV();
        return buildFileResponseFromResource(javaCV);
    }


    private ResponseEntity<?> buildFileResponseFromResource(Resource fileResource) {
        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + fileResource.getFilename() + "\"";
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(fileResource);

    }
}
