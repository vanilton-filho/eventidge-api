package com.eventidge.eventidgeapi.domain.repository;

import com.eventidge.eventidgeapi.domain.model.meetup.Meetup;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MeetupRepository extends CustomJpaRepository<Meetup, Long> {

    Optional<Meetup> findByCode(String code);
    Optional<Meetup> findByTag(String tag);

}
