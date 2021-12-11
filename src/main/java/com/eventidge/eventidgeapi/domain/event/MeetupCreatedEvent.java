package com.eventidge.eventidgeapi.domain.event;

import com.eventidge.eventidgeapi.domain.model.meetup.Meetup;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MeetupCreatedEvent {

    private Meetup meetup;

}
