package com.eventidge.eventidgeapi.api.v1.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonModel {

    private String name;
    private String cpf;
    private AddressModel address;

}
