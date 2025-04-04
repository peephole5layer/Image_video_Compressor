package com.image_video_compressor.compressor.controller;

import com.image_video_compressor.compressor.service.CompressionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class CompressorController {
    @Autowired
    private CompressionService compressionService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            File compressed = compressionService.compressImage(file);
            return ResponseEntity.ok("Compressed file saved: " + compressed.getAbsolutePath());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Compression failed");
        }
    }

}
