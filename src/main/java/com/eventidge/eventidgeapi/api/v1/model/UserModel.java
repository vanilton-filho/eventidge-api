package com.eventidge.eventidgeapi.api.v1.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class UserModel {

    private Long id;
    private String username;
    private String email;
    private String phone;
    private OffsetDateTime createdAt;
    private PersonModel person;
    private OrgModel organization;

}
