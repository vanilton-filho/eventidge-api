package com.eventidge.eventidgeapi.domain.service;

import com.eventidge.eventidgeapi.domain.exception.BusinessException;
import com.eventidge.eventidgeapi.domain.model.meetup.MeetupRegistration;
import com.eventidge.eventidgeapi.domain.repository.MeetupRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MeetupRegistrationService {

    @Autowired
    private MeetupRegistrationRepository meetupRegistryRepository;


    public MeetupRegistration save(MeetupRegistration meetupRegistration) {
        var userId = meetupRegistration.getParticipant().getId();
        var meetup = meetupRegistration.getMeetup();
        Optional<MeetupRegistration> meetupRegistrations = meetupRegistryRepository.findMeetupsByUserId(userId, meetup);
        if (meetupRegistrations.isPresent()) {
            throw new BusinessException("User is not allowed to register again");
        }

        meetupRegistration.confirmRegistration();
        return this.meetupRegistryRepository.save(meetupRegistration);
    }

}
