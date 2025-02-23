package swe304.swe304_1.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class StorageService {
    @Value("${file.upload-dir}")
    private String localUploadDir;

    @Value("${cloud.provider}")
    private String cloudProvider;

    public String storeFile(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        // TODO: if image dir is placed in resources it requires a rebuild of the project to show the images
        if (cloudProvider.equals("local")) {
            File uploadDir = new File(localUploadDir);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            Path targetPath = Path.of(localUploadDir, fileName);
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            return "/images/" + fileName;
        } else if (cloudProvider.equals("aws")) {
            return uploadToS3(file, fileName);
        } else if (cloudProvider.equals("azure")) {
            return uploadToAzureBlob(file, fileName);
        }
        return null;
    }

    private String uploadToS3(MultipartFile file, String fileName) {
        //TODO: add logic for S3
        return "https://s3.amazonaws.com//" + fileName;
    }

    private String uploadToAzureBlob(MultipartFile file, String fileName) {
        //TODO: add logic for Azure
        return "https://myapp.blob.core.windows.net//" + fileName;
    }
}
