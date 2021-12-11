package com.eventidge.eventidgeapi.domain.service;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;
import java.util.UUID;

public interface FileStorageService {

    FileRecovered toRecover(String fileName);
    void toStore(NewFile newFile);
    void remove(String fileName);

    default void toReplace(String fileName, NewFile newFile) {
        this.toStore(newFile);

        if (fileName != null) {
            this.remove(fileName);
        }
    }

    default String generateFileName(String fileName) {
        return UUID.randomUUID() + "_" + fileName;
    }

    @Getter
    @Builder
    class NewFile {
        private String fileName;
        private String contentType;
        private InputStream inputStream;
    }

    @Getter
    @Builder
    class FileRecovered {
        private InputStream inputStream;
        private String url;

        public boolean hasInputStream() {
            return inputStream != null;
        }

        public boolean hasUrl() {
            return url != null;
        }
    }
}
