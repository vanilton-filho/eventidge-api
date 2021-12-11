package com.eventidge.eventidgeapi.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserOrgWithPasswordInput extends UserOrganizationModelInput {

    @NotBlank
    private String password;

}
