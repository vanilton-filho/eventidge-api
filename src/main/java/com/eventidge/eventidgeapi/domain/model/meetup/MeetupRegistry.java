package com.eventidge.eventidgeapi.domain.model.meetup;

import com.eventidge.eventidgeapi.domain.model.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class MeetupRegistry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime entryTime;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User participant;

    private Boolean isAccordingTerms;

}
