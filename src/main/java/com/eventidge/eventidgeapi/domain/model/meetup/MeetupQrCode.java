package com.eventidge.eventidgeapi.domain.model.meetup;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class MeetupQrCode {

    @Id
    @Column(name = "meetup_id")
    @EqualsAndHashCode.Include
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Meetup meetup;

    private String fileName;
    private String contentType;
    private Long length;

    public Long getMeetupId() {
        return getMeetup() != null ? meetup.getId() : null;
    }
}
