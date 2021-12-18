package com.eventidge.eventidgeapi.domain.exception;

public class MeetupByCodeNotFoundException extends NotFoundException {

    public MeetupByCodeNotFoundException(String code) {
        super(String.format("Not exists event with code %s", code));
    }
}
