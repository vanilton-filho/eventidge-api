package com.eventidge.eventidgeapi.domain.service;

import com.eventidge.eventidgeapi.domain.exception.*;
import com.eventidge.eventidgeapi.domain.model.meetup.Meetup;
import com.eventidge.eventidgeapi.domain.model.meetup.MeetupPrizeDraw;
import com.eventidge.eventidgeapi.domain.model.user.User;
import com.eventidge.eventidgeapi.domain.repository.MeetupPrizeDrawRepository;
import com.eventidge.eventidgeapi.domain.repository.MeetupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MeetupService {

    @Autowired
    private MeetupRepository meetupRepository;

    @Autowired
    private UserService userService;

    public Meetup findOrFail(Long id) {
        return meetupRepository.findById(id)
                .orElseThrow(() -> new MeetupNotFoundException(id));
    }

    public Meetup getByCode(String code) {
        return meetupRepository.findByCode(code)
                .orElseThrow(() -> new MeetupByCodeNotFoundException(code));
    }

    public Meetup getByTag(String tag) {
        return meetupRepository.findByTag(tag)
                .orElseThrow(() -> new MeetupByTagNotFoundException(tag));
    }

    public Meetup save(Meetup meetup) {
        Long userId = meetup.getResponsible().getId();
        User responsible = userService.findOrFail(userId);

        Optional<Meetup> meetupFound = meetupRepository.findByTag(meetup.getTag());
        if (meetupFound.isPresent()) {
            throw new BusinessException("Duplicating tags is not allowed.");
        }

        meetup.setResponsible(responsible);
        meetup.confirmCreation();
        return this.meetupRepository.save(meetup);
    }

}
