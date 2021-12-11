package com.eventidge.eventidgeapi.api.v1.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class UserPersonModel {

    private Long id;
    private String username;
    private String email;
    private String phone;
    private OffsetDateTime createdAt;
    private PersonModel person;

}
