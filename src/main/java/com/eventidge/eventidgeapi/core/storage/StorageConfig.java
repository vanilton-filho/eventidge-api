package com.eventidge.eventidgeapi.core.storage;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.eventidge.eventidgeapi.domain.service.FileStorageService;
import com.eventidge.eventidgeapi.infrastructure.service.storage.LocalFileStorageService;
import com.eventidge.eventidgeapi.infrastructure.service.storage.S3FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {

    @Autowired
    private StorageProperties storageProperties;

    @Bean
    @ConditionalOnProperty(name = "eventidge.storage.type-storage", havingValue = "s3")
    public AmazonS3 amazonS3() {
        var credentials = new BasicAWSCredentials(
                storageProperties.getS3().getAccessKeyId(),
                storageProperties.getS3().getSecretAccessKey()
        );

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(storageProperties.getS3().getRegion())
                .build();
    }


    @Bean
    public FileStorageService fileStorageService() {
        if (storageProperties.getTypeStorage().equals(StorageProperties.TypeStorage.S3)) {
            return new S3FileStorageService();
        } else {
            return new LocalFileStorageService();
        }
    }
}
