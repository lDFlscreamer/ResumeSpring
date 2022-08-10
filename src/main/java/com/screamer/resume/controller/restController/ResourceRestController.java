package com.screamer.resume.controller.restController;

import com.screamer.resume.service.ResourceService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "resource")
public class ResourceRestController {

    final
    ResourceService resourceService;

    public ResourceRestController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @GetMapping("/CV/Java")
    public ResponseEntity<?> getImageDynamicType() {
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
