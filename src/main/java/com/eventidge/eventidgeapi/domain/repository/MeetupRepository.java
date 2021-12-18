package com.eventidge.eventidgeapi.domain.repository;

import com.eventidge.eventidgeapi.domain.model.meetup.Meetup;
import com.eventidge.eventidgeapi.domain.model.meetup.MeetupQrCode;
import com.eventidge.eventidgeapi.domain.model.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MeetupRepository extends CustomJpaRepository<Meetup, Long>, MeetupQrCodeRepositoryQueries {

    Optional<Meetup> findByCode(String code);
    Optional<Meetup> findByTag(String tag);
    boolean existsByTag(String tag);

    @Query("from MeetupQrCode as mqr where mqr.meetup.tag = :meetupTag")
    Optional<MeetupQrCode> findQrCodeByMeetupId(String meetupTag);

    @Query("from User as u, MeetupRegistration as mr where u.id = mr.participant.id and mr.meetup.tag = :tag")
    List<User> findAllParticipantsByMeetupTag(String tag);
}
