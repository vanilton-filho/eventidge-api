package com.eventidge.eventidgeapi.api.v1.controller;

import com.eventidge.eventidgeapi.api.utils.MediaTypeHelper;
import com.eventidge.eventidgeapi.api.v1.model.UserPhotoModel;
import com.eventidge.eventidgeapi.api.v1.model.input.UserPhotoInput;
import com.eventidge.eventidgeapi.api.v1.serializers.UserPhotoSerializer;
import com.eventidge.eventidgeapi.domain.exception.NotFoundException;
import com.eventidge.eventidgeapi.domain.model.user.User;
import com.eventidge.eventidgeapi.domain.model.user.UserPhoto;
import com.eventidge.eventidgeapi.domain.service.FileStorageService;
import com.eventidge.eventidgeapi.domain.service.UserPhotoStorageService;
import com.eventidge.eventidgeapi.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/users/{userId}/profile-photo", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserPhotoController {

    @Autowired
    private UserPhotoStorageService userPhotoStorageService;

    @Autowired
    private UserService userService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private UserPhotoSerializer userPhotoSerializer;

    @PutMapping
    public UserPhotoModel upload(
            @PathVariable Long userId, @Valid UserPhotoInput userPhotoInput, MultipartFile file) throws IOException {
        User user = userService.findOrFail(userId);
        UserPhoto photo = new UserPhoto();
        photo.setUser(user);
        photo.setContentType(file.getContentType());
        photo.setLength(file.getSize());
        photo.setFileName(file.getOriginalFilename());

        UserPhoto userPhotoSaved = userPhotoStorageService.save(photo, file.getInputStream());
        return userPhotoSerializer.toModel(userPhotoSaved);
    }

    @GetMapping
    public ResponseEntity<UserPhotoModel> getById(@PathVariable Long userId) {
        UserPhoto userPhoto = userPhotoStorageService.findOrFail(userId);
        return ResponseEntity.ok(userPhotoSerializer.toModel(userPhoto));
    }

    @GetMapping(produces = MediaType.ALL_VALUE)
    public ResponseEntity<?> download(@PathVariable Long userId, @RequestHeader(name = "accept") String acceptHeader) throws HttpMediaTypeNotAcceptableException {
        try {
            UserPhoto userPhoto = userPhotoStorageService.findOrFail(userId);
            MediaType photoMediaType = MediaType.parseMediaType(userPhoto.getContentType());
            List<MediaType> acceptedMediaTypes = MediaType.parseMediaTypes(acceptHeader);

            MediaTypeHelper.checkMediaTypes(photoMediaType, acceptedMediaTypes);

            FileStorageService.FileRecovered userPhotoRecovered = fileStorageService.toRecover(userPhoto.getFileName());
            if (userPhotoRecovered.hasUrl()) {
                return ResponseEntity
                        .status(HttpStatus.FOUND)
                        .contentLength(userPhoto.getLength())
                        .contentType(photoMediaType)
                        .header(HttpHeaders.LOCATION, userPhotoRecovered.getUrl())
                        .header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*")
                        .build();
            } else {
                    return ResponseEntity.ok()
                            .contentLength(userPhoto.getLength())
                            .contentType(photoMediaType)
                            .header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*")
                            .body(new InputStreamResource(userPhotoRecovered.getInputStream()));
            }
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
