package com.eventidge.eventidgeapi.infrastructure.service.email;

import com.eventidge.eventidgeapi.domain.service.EmailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

@Component
public class EmailTemplateService {

    @Autowired
    private Configuration configuration;

    public String buildTemplate(EmailService.EmailMessage emailMessage) {
        try {
            Template template = configuration.getTemplate(emailMessage.getBody());
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, emailMessage.getVars());
        } catch (Exception e) {
            throw new EmailException("Not was possible build template for the email", e);
        }
    }

}
