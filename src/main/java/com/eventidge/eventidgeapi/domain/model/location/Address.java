package com.eventidge.eventidgeapi.domain.model.location;

import lombok.Data;

import javax.persistence.*;

@Data
@Embeddable
public class Address {

    @Column(name = "address_street")
    private String street;

    @Column(name = "address_number")
    private String number; //ex:42b

    @Column(name = "address_neighborhood")
    private String neighborhood;

    @Column(name = "address_zip_code")
    private String zipCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_city_id")
    private City city;

}
