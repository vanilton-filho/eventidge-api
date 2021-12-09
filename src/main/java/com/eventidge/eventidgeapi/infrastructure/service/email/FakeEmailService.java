package com.eventidge.eventidgeapi.infrastructure.service.email;

import com.eventidge.eventidgeapi.core.email.EmailProperties;
import com.eventidge.eventidgeapi.domain.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class FakeEmailService implements EmailService {

    @Autowired
    private EmailProperties emailProperties;

    @Autowired
    private EmailTemplateService emailTemplateService;

    @Override
    public void send(EmailMessage message) {
        String body = emailTemplateService.buildTemplate(message);
        log.info("[EVENTIDGE - FAKE EMAIL] ~> \nSender:{} | To:{}\n{}", emailProperties.getSender(),  message.getRecipients(), body);
    }

}
