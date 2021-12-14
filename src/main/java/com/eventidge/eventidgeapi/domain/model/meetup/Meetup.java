package com.eventidge.eventidgeapi.domain.model.meetup;

import com.eventidge.eventidgeapi.domain.event.MeetupCreatedEvent;
import com.eventidge.eventidgeapi.domain.model.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Meetup extends AbstractAggregateRoot<Meetup> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String code;

    @Column(unique = true)
    private String tag;

    private String name;
    private String description;
    private Boolean isEnabled = Boolean.FALSE;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User responsible;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime updatedAt;

    @PrePersist
    public void generateMeetupCode() {
        setCode(UUID.randomUUID().toString());
    }

    public void confirmCreation() {
        registerEvent(new MeetupCreatedEvent(this));
    }

}
