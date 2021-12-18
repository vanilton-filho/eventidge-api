package com.eventidge.eventidgeapi.domain.exception;

public class MeetupByTagNotFoundException extends NotFoundException {

    public MeetupByTagNotFoundException(String tag) {
        super(String.format("Not exists event with tag %s", tag));
    }
}
