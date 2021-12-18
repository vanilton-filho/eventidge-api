package com.eventidge.eventidgeapi.domain.service;

import com.eventidge.eventidgeapi.domain.exception.ConflictException;
import com.eventidge.eventidgeapi.domain.exception.NotFoundException;
import com.eventidge.eventidgeapi.domain.model.meetup.Meetup;
import com.eventidge.eventidgeapi.domain.model.meetup.MeetupPrizeDraw;
import com.eventidge.eventidgeapi.domain.model.user.User;
import com.eventidge.eventidgeapi.domain.repository.MeetupPrizeDrawRepository;
import com.eventidge.eventidgeapi.domain.repository.MeetupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MeetupPrizeDrawService {

    @Autowired
    private MeetupPrizeDrawRepository meetupPrizeDrawRepository;

    @Autowired
    private MeetupRepository meetupRepository;

    @Autowired
    private MeetupService meetupService;

    public MeetupPrizeDraw findMeetupPrizeDrawByTag(String tag) {
        return meetupPrizeDrawRepository.findTopByTag(tag)
                .orElseThrow(() -> new NotFoundException("There are no records for this meetup"));
    }

    public List<MeetupPrizeDraw> findAllUsersByMeetup(Meetup meetup) {
        return meetupPrizeDrawRepository.findUsersByMeetup(meetup);
    }


    @Transactional
    public MeetupPrizeDraw getWinnerPrizeDraw(String tag, String prize) {
        var meetup = meetupService.getByTag(tag);

        var participants = meetupRepository.findAllParticipantsByMeetupTag(tag);
        var winnerNumber = getRandomNumber(0, participants.size());
        var winner = participants.get(winnerNumber);
        MeetupPrizeDraw meetupPrizeDraw = new MeetupPrizeDraw();
        meetupPrizeDraw.setMeetup(meetup);
        meetupPrizeDraw.setUser(winner);
        meetupPrizeDraw.setTag(tag);
        meetupPrizeDraw.setPrize(prize);
        meetupPrizeDrawRepository.save(meetupPrizeDraw);

        return meetupPrizeDraw;
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
