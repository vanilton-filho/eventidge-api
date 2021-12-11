package com.eventidge.eventidgeapi.domain.exception;

public class UserPhotoNotFoundException extends NotFoundException {

    public UserPhotoNotFoundException(String message) {
        super(message);
    }

    public UserPhotoNotFoundException(Long userId) {
        this(String.format("Not exits user photo with id %d", userId));
    }

}
