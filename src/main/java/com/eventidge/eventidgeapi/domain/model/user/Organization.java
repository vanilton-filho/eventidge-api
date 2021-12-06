package com.eventidge.eventidgeapi.domain.model.user;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class Organization {

    @Column(name = "organization_name")
    private String name;

    @Column(name = "organization_email")
    private String email;

    @Column(name = "organization_phone")
    private String phone;

    @Column(name = "organization_is_verified")
    private Boolean isVerified;

}
