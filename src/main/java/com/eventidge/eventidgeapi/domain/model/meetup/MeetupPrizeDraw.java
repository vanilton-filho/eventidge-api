package com.eventidge.eventidgeapi.domain.model.meetup;

import com.eventidge.eventidgeapi.domain.model.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class MeetupPrizeDraw {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String code;

    private String prize;

    @ManyToOne
    @JoinColumn(name = "meetup_id")
    private Meetup meetup;

    private String tag;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    private OffsetDateTime createdAt;

    @PrePersist
    public void generateCode() {
        setCode(UUID.randomUUID().toString());
    }

}
