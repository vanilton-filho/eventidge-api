package com.eventidge.eventidgeapi.domain.model.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String code;
    private String tag;

    private String name;
    private String description;
    private Boolean isEnabled = Boolean.FALSE;

    @CreationTimestamp
    private OffsetDateTime createdAt;

    @PrePersist
    public void generateEventCode() {
        setCode(UUID.randomUUID().toString());
    }

}
