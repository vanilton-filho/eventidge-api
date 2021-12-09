package com.eventidge.eventidgeapi.domain.service;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;

import java.util.Map;
import java.util.Set;

public interface EmailService {

    void send(EmailMessage message);

    @Builder
    @Getter
    class EmailMessage {

        @Singular
        private Set<String> recipients;

        @NonNull
        private String subject;

        @NonNull
        private String body;

        @Singular("variable")
        private Map<String, Object> vars;
    }
}
