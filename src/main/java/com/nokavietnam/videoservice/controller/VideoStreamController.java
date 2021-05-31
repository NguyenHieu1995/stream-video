package com.nokavietnam.videoservice.controller;

import com.nokavietnam.videoservice.service.VideoStreamingSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

/**
 * @author : NOKA NGUYEN
 * @since : 6/1/2021, Tue
 **/
@RestController
public class VideoStreamController {

    @Autowired
    VideoStreamingSevice service;

    public static final String VideoUploadingDir = System.getProperty("user.dir") + "/Uploads/Posts/Videos";

    @GetMapping(value = "/video", produces = "application/octet-stream")
    public ResponseEntity<ResourceRegion> getVideo(@RequestHeader(value = "Range", required = false) String rangeHeader)
            throws IOException {

        String name = "video";

        if (!new File(VideoUploadingDir).exists()) {
            new File(VideoUploadingDir).mkdirs();
        }
        return service.getVideoRegion(rangeHeader, VideoUploadingDir, name);

    }
}
