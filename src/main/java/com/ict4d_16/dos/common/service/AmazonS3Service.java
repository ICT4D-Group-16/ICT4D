package com.ict4d_16.dos.common.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class AmazonS3Service {
    @Value("${aws.access-key}")
    private String accessKeyId;

    @Value("${aws.secret-key}")
    private String secretAccessKey;

    @Value("${aws.region}")
    private String region;

    @Value("${aws.bucket-name}")
    private String bucketName;

    @Value("${aws.public-url}")
    private String publicUrl;

    public String putObject(MultipartFile file) {
        try {
            InputStream inputStream = new BufferedInputStream(file.getInputStream());
            String filename = file.getOriginalFilename();
            String key = "public/" + UUID.randomUUID().toString() + "-" + filename;
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(filename.substring(filename.lastIndexOf(".") + 1));
            objectMetadata.setContentLength(file.getSize());
            getAmazonS3().putObject(bucketName, key, inputStream, objectMetadata);
            return publicUrl + key;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<InputStreamResource> downloadObject(String key) {
        S3Object s3Object = getAmazonS3().getObject(bucketName, key);
        return ResponseEntity.ok()
                .contentLength(s3Object.getObjectMetadata().getContentLength())
                .contentType(MediaType.parseMediaType(s3Object.getObjectMetadata().getContentType()))
                .cacheControl(CacheControl.maxAge(365, TimeUnit.DAYS))
                .body(new InputStreamResource(s3Object.getObjectContent()));
    }

    private AmazonS3 getAmazonS3() {
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKeyId, secretAccessKey)))
                .withRegion(region)
                .build();
    }
}
