package com.eventidge.eventidgeapi.domain.model.meetup;

import com.eventidge.eventidgeapi.domain.event.UserMeetupRegistrationEvent;
import com.eventidge.eventidgeapi.domain.model.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class MeetupRegistration  extends AbstractAggregateRoot<MeetupRegistration> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime entryTime;

    @OneToOne
    private Meetup meetup;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User participant;

    private Boolean isAccordingTerms;

    public void confirmRegistration() {
        registerEvent(new UserMeetupRegistrationEvent(this));
    }

}
