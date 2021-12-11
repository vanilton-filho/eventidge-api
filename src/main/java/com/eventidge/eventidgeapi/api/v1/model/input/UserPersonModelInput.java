package com.eventidge.eventidgeapi.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserPersonModelInput extends UserModelInput {

    @NotBlank
    private String personName;

    @CPF
    @NotBlank
    private String cpf;

}
