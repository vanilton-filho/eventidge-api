package com.eventidge.eventidgeapi.domain.model.user;

import com.eventidge.eventidgeapi.domain.model.location.Address;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Embeddable
public class Person {

    @Column(name = "person_name")
    private String name;

    @Column(name = "person_gender")
    private String gender;

    @Column(name = "person_birth_date")
    private LocalDate birthDate;

    @Embedded
    private Address address;

    @Embedded
    private NaturalPerson naturalPerson;
}
