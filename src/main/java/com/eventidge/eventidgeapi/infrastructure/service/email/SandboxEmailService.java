package com.eventidge.eventidgeapi.infrastructure.service.email;

import com.eventidge.eventidgeapi.core.email.EmailProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class SandboxEmailService extends SmtpEmailService {

    @Autowired
    private EmailProperties emailProperties;

    @Override
    protected MimeMessage createMimeMessage(EmailMessage message) throws MessagingException {
        MimeMessage mimeMessage = super.createMimeMessage(message);
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        helper.setTo(emailProperties.getSandbox().getRecipient());
        return mimeMessage;
    }
}
