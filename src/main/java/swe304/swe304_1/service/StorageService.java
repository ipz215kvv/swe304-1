package swe304.swe304_1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.CannedAccessControlList;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class StorageService {

    @Value("${AWS_S3_REGION}")
    private String s3Region;

    @Value("${AWS_S3_BUCKET_NAME}")
    private String bucketName;

    private final AmazonS3 s3Client;

    @Autowired
    public StorageService(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    public String storeFile(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        try (InputStream stream = file.getInputStream()) {
            PutObjectRequest request = new PutObjectRequest(bucketName, fileName, stream, null)
                    .withCannedAcl(CannedAccessControlList.PublicRead);
            s3Client.putObject(request);
        } catch (AmazonServiceException e) {
            e.printStackTrace();
        }

        return String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, s3Region, fileName);
    }

    public void deleteFile(String fileName) {
        try {
            s3Client.deleteObject(bucketName, fileName);
        } catch (AmazonServiceException e) {
            e.printStackTrace();
        }
    }
}
