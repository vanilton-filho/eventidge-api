package com.eventidge.eventidgeapi.domain.listener;

import com.eventidge.eventidgeapi.domain.event.UserMeetupRegistrationEvent;
import com.eventidge.eventidgeapi.domain.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class UserMeetupCreatedNotificationListener {

    @Autowired
    private EmailService emailService;

    @TransactionalEventListener
    public void onUserMeetupRegistration(UserMeetupRegistrationEvent event) {

        var meetupRegistration = event.getMeetupRegistration();

        var participant = meetupRegistration.getParticipant().getPerson().getName();
        var meetup = meetupRegistration.getMeetup().getName();
        var recipient = meetupRegistration.getParticipant().getEmail();
        var message = EmailService.EmailMessage.builder()
                .subject("Parabéns " + participant + "! Inscrição em " + meetup + " realizada!" )
                .body("user-meetup-registration.html")
                .variable("meetupRegistration", meetupRegistration)
                .recipient(recipient)
                .build();

        emailService.send(message);
    }
}
