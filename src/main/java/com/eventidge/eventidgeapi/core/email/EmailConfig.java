package com.eventidge.eventidgeapi.core.email;

import com.eventidge.eventidgeapi.domain.service.EmailService;
import com.eventidge.eventidgeapi.infrastructure.service.email.FakeEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {

    @Autowired
    private EmailProperties emailProperties;

    @Bean
    public EmailService emailService() {
        return new FakeEmailService();
    }
}
