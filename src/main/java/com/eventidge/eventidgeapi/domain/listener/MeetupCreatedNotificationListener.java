package com.eventidge.eventidgeapi.domain.listener;

import com.eventidge.eventidgeapi.domain.event.MeetupCreatedEvent;
import com.eventidge.eventidgeapi.domain.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class MeetupCreatedNotificationListener {

    @Autowired
    private EmailService emailService;

    @TransactionalEventListener
    public void onMeetupCreated(MeetupCreatedEvent event) {
        var meetup = event.getMeetup();

        var message = EmailService.EmailMessage.builder()
                .subject("Hey, your meetup: " + meetup.getName() + " was created!")
                .body("meetup-created.html")
                .variable("meetup", meetup)
                .recipient(meetup.getResponsible().getEmail())
                .build();

        emailService.send(message);
    }
}
