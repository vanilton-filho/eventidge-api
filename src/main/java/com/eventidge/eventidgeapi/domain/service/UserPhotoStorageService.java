package com.eventidge.eventidgeapi.domain.service;

import com.eventidge.eventidgeapi.domain.exception.UserPhotoNotFoundException;
import com.eventidge.eventidgeapi.domain.model.user.UserPhoto;
import com.eventidge.eventidgeapi.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.time.OffsetDateTime;
import java.util.Optional;

@Service
public class UserPhotoStorageService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileStorageService fileStorageService;

    public UserPhoto findOrFail(Long userId) {
        return userRepository.findPhotoById(userId)
                .orElseThrow(() -> new UserPhotoNotFoundException(userId));
    }

    @Transactional
    public UserPhoto save(UserPhoto userPhoto, InputStream inputStream) {
        Long userId = userPhoto.getUserId();
        String fileName = fileStorageService.generateFileName(userPhoto.getFileName());
        String oldFileName = null;

        Optional<UserPhoto> oldPhoto = userRepository.findPhotoById(userId);
        if (oldPhoto.isPresent()) {
            oldFileName = oldPhoto.get().getFileName();
            userRepository.delete(oldPhoto.get());
        }

        userPhoto.setFileName(fileName);
        userPhoto.setUpdatedAt(OffsetDateTime.now());
        userPhoto = userRepository.save(userPhoto);
        userRepository.flush();

        FileStorageService.NewFile newFile = FileStorageService.NewFile
                .builder()
                .fileName(userPhoto.getFileName())
                .contentType(userPhoto.getContentType())
                .inputStream(inputStream)
                .build();
        fileStorageService.toReplace(oldFileName, newFile);

        return userPhoto;
    }

    @Transactional
    public void delete(Long userId) {
        var userPhoto = findOrFail(userId);

        userRepository.delete(userPhoto);
        userRepository.flush();
        fileStorageService.remove(userPhoto.getFileName());
    }
}
