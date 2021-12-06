package com.eventidge.eventidgeapi.api.v1.model;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class UserModel {

    private Long id;
    private String username;
    private String email;
    private OffsetDateTime createdAt;
    private PersonModel person;

}
