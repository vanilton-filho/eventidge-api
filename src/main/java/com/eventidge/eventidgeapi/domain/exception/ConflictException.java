package com.eventidge.eventidgeapi.domain.exception;

public class ConflictException extends BusinessException {

    public ConflictException(String entry) {
        super(String.format("There is already a record with this data: %s", entry));
    }

}
