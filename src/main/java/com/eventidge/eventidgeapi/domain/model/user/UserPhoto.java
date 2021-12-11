package com.eventidge.eventidgeapi.domain.model.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

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

    public Long getUserId() {
        return getUser() != null ? user.getId() : null;
    }
}
