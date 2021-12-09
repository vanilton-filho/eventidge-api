package com.eventidge.eventidgeapi.domain.exception;

public class EventByTagNotFoundException extends NotFoundException {

    public EventByTagNotFoundException(String tag) {
        super(String.format("Not exists event with tag %s", tag));
    }
}
