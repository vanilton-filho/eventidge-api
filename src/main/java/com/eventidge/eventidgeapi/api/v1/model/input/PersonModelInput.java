package com.eventidge.eventidgeapi.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PersonModelInput {

    private String name;
    private String cpf;
    private AddressModelInput address;

}
