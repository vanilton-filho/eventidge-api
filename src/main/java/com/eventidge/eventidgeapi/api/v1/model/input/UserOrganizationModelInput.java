package com.eventidge.eventidgeapi.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserOrganizationModelInput extends UserModelInput {

    @NotBlank
    private String organizationName;

    @NotBlank
    private String organizationEmail;

    @NotBlank
    private String organizationPhone;


}
