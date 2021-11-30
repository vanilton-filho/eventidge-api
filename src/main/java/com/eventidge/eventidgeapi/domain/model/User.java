package com.eventidge.eventidgeapi.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String username;
    private String password;
    private String email;
    private String phone;

    @OneToOne
    private Person person;

    @CreationTimestamp
    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

    @Embedded
    private Organization organization;


}
