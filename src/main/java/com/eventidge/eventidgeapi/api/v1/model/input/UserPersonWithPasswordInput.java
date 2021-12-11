package com.eventidge.eventidgeapi.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserPersonWithPasswordInput extends UserPersonModelInput {

    @NotBlank
    @Size(min = 8)
    private String password;

}
