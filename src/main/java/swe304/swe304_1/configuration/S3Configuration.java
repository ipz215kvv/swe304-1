package swe304.swe304_1.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Configuration {

    String accessKey = System.getProperty("AWS_ACCESS_KEY");
    String secretKey = System.getProperty("AWS_SECRET_KEY");
    String s3Region = System.getProperty("AWS_S3_REGION");

    @Bean
    public AmazonS3 getAmazonS3Client() {
        AWSCredentials credentails = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonS3ClientBuilder.standard()
        .withCredentials(new AWSStaticCredentialsProvider(credentails))
                .withRegion(s3Region)
                .build();
    }

}