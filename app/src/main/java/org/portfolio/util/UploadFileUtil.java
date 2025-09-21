package org.portfolio.util;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class UploadFileUtil {

    private static final String BUCKET_NAME = "myportfolio_uploads"; // your GCS bucket

    public static String uploadFile(MultipartFile file, String fileType) {
        try {
            // Unique filename
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            String blobName = fileType + "/" + fileName;

            // Initialize GCS client
            Storage storage = StorageOptions.getDefaultInstance().getService();

            // Upload file to GCS
            BlobInfo blobInfo = BlobInfo.newBuilder(BUCKET_NAME, blobName).build();
            storage.create(blobInfo, file.getBytes());

            // Return public URL or GCS path
            return "https://storage.googleapis.com/" + BUCKET_NAME + "/" + blobName;

        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file: " + e.getMessage(), e);
        }
    }
}
