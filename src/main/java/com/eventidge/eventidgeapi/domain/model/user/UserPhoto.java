package com.eventidge.eventidgeapi.domain.model.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class UserPhoto {

    @Id
    @Column(name = "user_id")
    @EqualsAndHashCode.Include
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    private String fileName;
    private String contentType;
    private Long length;
    private OffsetDateTime updatedAt;


    public Long getUserId() {
        return getUser() != null ? user.getId() : null;
    }
}
