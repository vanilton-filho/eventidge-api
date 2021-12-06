package com.eventidge.eventidgeapi.domain.model.user;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class NaturalPerson {

    private String cpf;
    private String maritalStatus;

}
