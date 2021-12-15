package com.eventidge.eventidgeapi.api.v1.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class UserOrgModel {

    private Long id;
    private String username;
    private String email;
    private String phone;
    private String organizationName;
    private String organizationEmail;
    private String organizationPhone;
    private OffsetDateTime createdAt;

}
