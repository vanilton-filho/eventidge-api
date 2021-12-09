package com.eventidge.eventidgeapi.domain.exception;

public class EventByCodeNotFoundException extends NotFoundException {

    public EventByCodeNotFoundException(String code) {
        super(String.format("Not exists event with code %s", code));
    }
}
