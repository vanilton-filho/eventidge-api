package com.eventidge.eventidgeapi.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModelInput {

    private String username;
    private String password;
    private String email;
    private String personName;
    private String cpf;
}
