package swe304.swe304_1.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.CannedAccessControlList;

import swe304.swe304_1.configuration.S3Configuration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class StorageService {

    String s3Region = System.getProperty("AWS_S3_REGION");

    String bucketName = System.getProperty("AWS_S3_BUCKET_NAME");
    AmazonS3 s3Client = new S3Configuration().getAmazonS3Client();

    public String storeFile(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        try (InputStream stream = file.getInputStream()){
            PutObjectRequest request = new PutObjectRequest(bucketName, fileName, stream, null).withCannedAcl(CannedAccessControlList.PublicRead);
            s3Client.putObject(request);
        } catch (AmazonServiceException e) {
            e.printStackTrace();
        }

        return String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, s3Region, fileName);
    }

}
