package com.eventidge.eventidgeapi.api.v1.model;

import lombok.Getter;
import lombok.Setter;
import java.time.OffsetDateTime;

@Getter
@Setter
public class MeetupRegistrationModel {

    private Long id;
    private OffsetDateTime entryTime;
    private MeetupModel meetup;
    private UserOrgModel participant;

}
