package com.eventidge.eventidgeapi.domain.model.user;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class NaturalPerson {

    @Column(unique = true)
    private String cpf;

    private String maritalStatus;

}
