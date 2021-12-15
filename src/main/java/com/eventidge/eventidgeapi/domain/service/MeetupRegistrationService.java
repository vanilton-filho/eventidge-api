package com.eventidge.eventidgeapi.domain.service;

import com.eventidge.eventidgeapi.domain.exception.BusinessException;
import com.eventidge.eventidgeapi.domain.model.meetup.MeetupRegistration;
import com.eventidge.eventidgeapi.domain.model.user.User;
import com.eventidge.eventidgeapi.domain.repository.MeetupRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeetupRegistrationService {

    @Autowired
    private MeetupRegistrationRepository meetupRegistryRepository;

    @Autowired
    private UserService userService;

    public MeetupRegistration save(MeetupRegistration meetupRegistry) {
        var userId = meetupRegistry.getParticipant().getId();
        var meetup = meetupRegistry.getMeetup();
        Optional<MeetupRegistration> meetupRegistrations = meetupRegistryRepository.findMeetupsByUserId(userId, meetup);
        if (meetupRegistrations.isPresent()) {
            throw new BusinessException("User is not allowed to register again");
        }

        return this.meetupRegistryRepository.save(meetupRegistry);
    }

}
