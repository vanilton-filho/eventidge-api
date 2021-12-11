package com.eventidge.eventidgeapi.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserOrgWithPasswordInput extends UserOrganizationModelInput {

    @NotBlank
    @Size(min = 8) // É importante considerar orientar para segurança em todas as pontas
    private String password;

}
