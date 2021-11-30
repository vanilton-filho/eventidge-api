package com.eventidge.eventidgeapi.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModelInput {

    private String username;
    private String email;
    private PersonModelInput person;
}
