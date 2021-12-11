package com.eventidge.eventidgeapi.domain.service;

import com.eventidge.eventidgeapi.domain.exception.EventByCodeNotFoundException;
import com.eventidge.eventidgeapi.domain.exception.EventByTagNotFoundException;
import com.eventidge.eventidgeapi.domain.model.meetup.Meetup;
import com.eventidge.eventidgeapi.domain.model.user.User;
import com.eventidge.eventidgeapi.domain.repository.MeetupRepository;
import com.eventidge.eventidgeapi.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeetupService {

    @Autowired
    private MeetupRepository meetupRepository;

    @Autowired
    private UserService userService;

    public Meetup getByCode(String code) {
        return meetupRepository.findByCode(code)
                .orElseThrow(() -> new EventByCodeNotFoundException(code));
    }

    public Meetup getByTag(String tag) {
        return meetupRepository.findByTag(tag)
                .orElseThrow(() -> new EventByTagNotFoundException(tag));
    }

    public Meetup save(Meetup meetup) {
        Long userId = meetup.getResponsible().getId();
        User responsible = userService.findOrFail(userId);

        meetup.setResponsible(responsible);
        meetup.confirmCreation();
        return this.meetupRepository.save(meetup);
    }

}
