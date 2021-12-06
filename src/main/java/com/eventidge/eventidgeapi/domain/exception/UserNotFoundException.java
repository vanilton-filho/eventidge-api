package com.eventidge.eventidgeapi.domain.exception;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Long id) {
        this(String.format("Not exists user with id %d", id));
    }
}
