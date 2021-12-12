package com.eventidge.eventidgeapi.core.email;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@Setter
@Component
@ConfigurationProperties("eventidge.email")
public class EmailProperties {

    @NonNull
    private String sender;

    private Sandbox sandbox = new Sandbox();

    private EmailType emailType = EmailType.FAKE;

    public enum EmailType {
        SMTP, FAKE, SANDBOX
    }

    @Getter
    @Setter
    public class Sandbox {
        private String recipient;
    }
}
