package com.eventidge.eventidgeapi.infrastructure.service.storage;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.eventidge.eventidgeapi.core.storage.StorageProperties;
import com.eventidge.eventidgeapi.domain.service.FileStorageService;
import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;

public class S3FileStorageService implements FileStorageService {

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private StorageProperties storageProperties;

    @Override
    public FileRecovered toRecover(String fileName) {

        String pathFile = getPath(fileName);
        URL url = amazonS3.getUrl(storageProperties.getS3().getBucket(), pathFile);

        return FileRecovered.builder()
                .url(url.toString())
                .build();
    }

    @Override
    public void toStore(NewFile newFile) {

        try {
            String pathFile = getPath(newFile.getFileName());
            var objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(newFile.getContentType());

            var putObjectRequest = new PutObjectRequest(
                    storageProperties.getS3().getBucket(),
                    pathFile,
                    newFile.getInputStream(),
                    objectMetadata
            ).withCannedAcl(CannedAccessControlList.PublicRead);

            amazonS3.putObject(putObjectRequest);
        } catch (Exception e) {
            throw new StorageException("Could not upload file to Amazon S3");
        }

    }

    @Override
    public void remove(String fileName) {

        try {
            String pathFile = getPath(fileName);
            var deleteObjectRequest = new DeleteObjectRequest(
                    storageProperties.getS3().getBucket(),
                    pathFile
            );
            amazonS3.deleteObject(deleteObjectRequest);
        } catch (Exception e) {
            throw new StorageException("Could not delete file from Amazon S3 bucket");
        }
    }

    private String getPath(String fileName) {
        return String.format("%s/%s", storageProperties.getS3().getPathFiles(), fileName);
    }
}
