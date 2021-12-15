package com.eventidge.eventidgeapi.domain.repository;

import com.eventidge.eventidgeapi.domain.model.meetup.Meetup;
import com.eventidge.eventidgeapi.domain.model.meetup.MeetupRegistration;
import com.eventidge.eventidgeapi.domain.model.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MeetupRegistrationRepository extends CustomJpaRepository<MeetupRegistration, Long> {

    @Query("from MeetupRegistration as m where m.participant.id = :userId and m.meetup = :meetup")
    Optional<MeetupRegistration> findMeetupsByUserId(Long userId, Meetup meetup);

}
