package com.eventidge.eventidgeapi.api.v1.controller;

import com.eventidge.eventidgeapi.api.v1.model.MeetupRegistrationModel;
import com.eventidge.eventidgeapi.api.v1.serializers.MeetupRegistrationSerializer;
import com.eventidge.eventidgeapi.domain.exception.BusinessException;
import com.eventidge.eventidgeapi.domain.model.meetup.Meetup;
import com.eventidge.eventidgeapi.domain.model.meetup.MeetupRegistration;
import com.eventidge.eventidgeapi.domain.model.user.User;
import com.eventidge.eventidgeapi.domain.repository.UserRepository;
import com.eventidge.eventidgeapi.domain.service.MeetupRegistrationService;
import com.eventidge.eventidgeapi.domain.service.MeetupService;
import com.eventidge.eventidgeapi.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/meetups-registrations/{meetupTag}")
public class MeetupRegistrationController {

    @Autowired
    private MeetupRegistrationService meetupRegistrationService;

    @Autowired
    private MeetupRegistrationSerializer meetupRegistrationSerializer;

    @Autowired
    private UserService userService;

    @Autowired
    private MeetupService meetupService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MeetupRegistrationModel register(@PathVariable String meetupTag) {
        MeetupRegistration meetupRegistration = new MeetupRegistration();

        User participant = userService.findOrFail(1L);
        meetupRegistration.setParticipant(participant);

        Meetup meetup = meetupService.getByTag(meetupTag);
        meetupRegistration.setMeetup(meetup);

        meetupRegistration.setIsAccordingTerms(true);

        MeetupRegistration meetupRegistrationCreated = meetupRegistrationService.save(meetupRegistration);
        MeetupRegistrationModel meetupRegistrationModel =
                meetupRegistrationSerializer.toModel(meetupRegistrationCreated);
        return meetupRegistrationModel;

    }

}
