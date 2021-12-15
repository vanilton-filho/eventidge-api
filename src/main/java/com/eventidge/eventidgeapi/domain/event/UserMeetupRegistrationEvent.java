package com.eventidge.eventidgeapi.domain.event;

import com.eventidge.eventidgeapi.domain.model.meetup.MeetupRegistration;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserMeetupRegistrationEvent {

    private MeetupRegistration meetupRegistration;

}
