package com.eventidge.eventidgeapi.domain.repository;

import com.eventidge.eventidgeapi.domain.model.meetup.Meetup;
import com.eventidge.eventidgeapi.domain.model.meetup.MeetupQrCode;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MeetupRepository extends CustomJpaRepository<Meetup, Long>, MeetupQrCodeRepositoryQueries {

    Optional<Meetup> findByCode(String code);
    Optional<Meetup> findByTag(String tag);

    @Query("from MeetupQrCode as mqr where mqr.meetup.tag = :meetupTag")
    Optional<MeetupQrCode> findQrCodeByMeetupId(String meetupTag);
}
