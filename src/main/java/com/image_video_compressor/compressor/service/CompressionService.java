package com.image_video_compressor.compressor.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
public class CompressionService {
    public File compressImage(MultipartFile file) throws IOException {
        // Convert MultipartFile to File
        File inputFile = convertToFile(file);

        // Compressed file output
        File compressedFile = new File("compressed_" + inputFile.getName());

        // Basic compression using DeflaterOutputStream (placeholder)
        try (
                FileInputStream fis = new FileInputStream(inputFile);
                FileOutputStream fos = new FileOutputStream(compressedFile);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                java.util.zip.DeflaterOutputStream dos = new java.util.zip.DeflaterOutputStream(bos)
        ) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                dos.write(buffer, 0, length);
            }
        }

        return compressedFile;
    }

    private File convertToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

}
