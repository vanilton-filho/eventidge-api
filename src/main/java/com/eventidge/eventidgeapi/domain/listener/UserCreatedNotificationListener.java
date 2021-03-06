package com.eventidge.eventidgeapi.domain.listener;

import com.eventidge.eventidgeapi.domain.event.UserCreatedEvent;
import com.eventidge.eventidgeapi.domain.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class UserCreatedNotificationListener {

    @Autowired
    private EmailService emailService;

    @TransactionalEventListener
    public void onUserCreated(UserCreatedEvent event) {
        var user = event.getUser();

        var message = EmailService.EmailMessage.builder()
                .subject(user.getUsername() + " Seja Bem-Vindo")
                .body("user-created.html")
                .variable("user", user)
                .recipient(user.getEmail())
                .build();

        emailService.send(message);
    }
}
