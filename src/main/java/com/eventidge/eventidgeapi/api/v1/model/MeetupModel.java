package com.eventidge.eventidgeapi.api.v1.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class MeetupModel {

    private String code;
    private String tag;
    private String name;
    private String description;
    private Boolean isEnabled;
    private OffsetDateTime createdAt;


}
