package org.portfolio.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class UploadFileUtil {

    public static String uploadFile(MultipartFile photo, String uploadDir, String fileType) {
        try {
            // Create directory if not exists
            File directory = new File(uploadDir, fileType);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Unique filename
            String fileName = System.currentTimeMillis() + "_" + photo.getOriginalFilename();

            // Save file
            File dest = new File(directory, fileName);
            photo.transferTo(dest);

            // Return relative URL (what frontend will use)
            return "/uploads/" + fileType + "/" + fileName;

        } catch (IOException e) {
            throw new RuntimeException("Failed to store file: " + e.getMessage(), e);
        }
    }
}
