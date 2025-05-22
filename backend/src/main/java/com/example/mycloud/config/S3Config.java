package com.example.mycloud.config; // Stellen Sie sicher, dass das Paket korrekt ist

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@Configuration
public class S3Config {

    @Value("${aws.region}")
    private String region;

    @Value("${aws.credentials.access-key}")
    private String accessKey;

    @Value("${aws.credentials.secret-key}")
    private String secretKey;

    @Value("${aws.s3.bucket}")
    private String bucket;

    @Bean
    public S3Client s3Client() {
        System.out.println("S3Config: Initialisiere S3Client mit Region: " + region);
        System.out.println("S3Config: Initialisiere S3Client mit Access Key (erste 4 Zeichen): "
                + (accessKey != null && accessKey.length() > 4 ? accessKey.substring(0, 4) : accessKey));
        System.out.println("S3Config: Bucket Name: " + bucket); 

        return S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(accessKey, secretKey)))
                .build();
    }

    @Bean
    public S3Presigner s3Presigner() {
        return S3Presigner.builder()
                .region(Region.of(region))
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(accessKey, secretKey)))
                .build();
    }
}