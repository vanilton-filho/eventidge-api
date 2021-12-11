package com.eventidge.eventidgeapi.infrastructure.service.storage;

import com.eventidge.eventidgeapi.core.storage.StorageProperties;
import com.eventidge.eventidgeapi.domain.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;

import java.nio.file.Files;
import java.nio.file.Path;

public class LocalFileStorageService implements FileStorageService {

    @Autowired
    public StorageProperties storageProperties;

    @Override
    public FileRecovered toRecover(String fileName) {
        try {
            Path filePath = getFilePath(fileName);
            return FileRecovered.builder()
                    .inputStream(Files.newInputStream(filePath))
                    .build();
        } catch (Exception e) {
            throw new StorageException("Could not recover file", e);
        }
    }

    @Override
    public void toStore(NewFile newFile) {
        try {
            Path filePath = getFilePath(newFile.getFileName());
            FileCopyUtils.copy(newFile.getInputStream(), Files.newOutputStream(filePath));
        } catch (Exception e) {
            throw new StorageException("Could not store file", e);
        }
    }

    @Override
    public void remove(String fileName) {
        try {
            Path filePath = getFilePath(fileName);
            Files.deleteIfExists(filePath);
        } catch (Exception e) {
            throw new StorageException("Could not delete file", e);
        }
    }

    private Path getFilePath(String fileName) {
        return storageProperties.getLocal()
                .getFileDirectory()
                .resolve(Path.of(fileName));
    }
}
