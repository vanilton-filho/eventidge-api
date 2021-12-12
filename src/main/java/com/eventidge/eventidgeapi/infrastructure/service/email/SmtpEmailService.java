package com.eventidge.eventidgeapi.infrastructure.service.email;

import com.eventidge.eventidgeapi.core.email.EmailProperties;
import com.eventidge.eventidgeapi.domain.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class SmtpEmailService implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailProperties emailProperties;

    @Autowired
    private EmailTemplateService emailTemplateService;

    @Override
    public void send(EmailMessage message) {
        try {
            MimeMessage mimeMessage = createMimeMessage(message);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new EmailException("Not possible send email", e);
        }
    }

    protected MimeMessage createMimeMessage(EmailMessage message) throws MessagingException {
        String body = emailTemplateService.buildTemplate(message);
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        helper.setFrom(emailProperties.getSender());
        helper.setTo(message.getRecipients().toArray(new String[0]));
        helper.setSubject(message.getSubject());
        helper.setText(body, true);
        return mimeMessage;
    }
}
