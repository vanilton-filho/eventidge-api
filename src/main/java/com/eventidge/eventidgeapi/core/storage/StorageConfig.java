package com.eventidge.eventidgeapi.core.storage;

import com.eventidge.eventidgeapi.domain.service.FileStorageService;
import com.eventidge.eventidgeapi.infrastructure.service.storage.LocalFileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {

    @Autowired
    private StorageProperties storageProperties;

    @Bean
    public FileStorageService fileStorageService() {
        return new LocalFileStorageService();
    }
}
