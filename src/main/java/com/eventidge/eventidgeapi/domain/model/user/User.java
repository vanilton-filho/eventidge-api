package com.eventidge.eventidgeapi.domain.model.user;

import com.eventidge.eventidgeapi.domain.eventflow.UserCreatedEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import org.springframework.data.domain.AbstractAggregateRoot;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class User extends AbstractAggregateRoot<User> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String username;
    private String password;
    private String email;
    private String phone;

    @Embedded
    private Person person;

    @CreationTimestamp
    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

    @Embedded
    private Organization organization;

    public void confirmRegistration() {
        registerEvent(new UserCreatedEvent(this));
    }
}
