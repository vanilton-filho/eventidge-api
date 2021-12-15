package com.eventidge.eventidgeapi.domain.exception;

public class MeetupNotFoundException extends NotFoundException {

    public MeetupNotFoundException(String message) {
        super(message);
    }

    public MeetupNotFoundException(Long id) {
        this(String.format("Not exists meetup with id %d", id));
    }
}
