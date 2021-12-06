package com.eventidge.eventidgeapi.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserModelInput {

    @NotBlank
    private String username;

    @Email
    private String email;

    @NotBlank
    private String personName;

    @CPF
    private String cpf;
}
