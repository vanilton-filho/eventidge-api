package com.eventidge.eventidgeapi.core.email;

import com.eventidge.eventidgeapi.domain.service.EmailService;
import com.eventidge.eventidgeapi.infrastructure.service.email.FakeEmailService;
import com.eventidge.eventidgeapi.infrastructure.service.email.SandboxEmailService;
import com.eventidge.eventidgeapi.infrastructure.service.email.SmtpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {

    @Autowired
    private EmailProperties emailProperties;

    @Bean
    public EmailService emailService() {
        if (emailProperties.getEmailType().equals(EmailProperties.EmailType.FAKE)) {
            return new FakeEmailService();
        } else if (emailProperties.getEmailType().equals(EmailProperties.EmailType.SMTP)) {
            return new SmtpEmailService();
        } else {
            return new SandboxEmailService();
        }
    }
}
