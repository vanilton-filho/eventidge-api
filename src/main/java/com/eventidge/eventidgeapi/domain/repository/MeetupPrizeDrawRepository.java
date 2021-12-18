package com.eventidge.eventidgeapi.domain.repository;

import com.eventidge.eventidgeapi.domain.model.meetup.Meetup;
import com.eventidge.eventidgeapi.domain.model.meetup.MeetupPrizeDraw;
import com.eventidge.eventidgeapi.domain.model.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MeetupPrizeDrawRepository extends CustomJpaRepository<MeetupPrizeDraw, Long> {

    boolean existsMeetupRaffleByMeetup(Meetup meetup);

    @Query("from MeetupPrizeDraw as m where m.meetup = :meetup")
    List<MeetupPrizeDraw> findUsersByMeetup(Meetup meetup);

    Optional<MeetupPrizeDraw> findTopByTag(String tag);

}
