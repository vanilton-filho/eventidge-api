package com.eventidge.eventidgeapi.domain.repository;

import com.eventidge.eventidgeapi.domain.model.event.Event;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends CustomJpaRepository<Event, Long> {

    Optional<Event> findByCode(String code);
    Optional<Event> findByTag(String tag);

}
