package com.eventidge.eventidgeapi.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class ApiException {

    private Integer status;
    private OffsetDateTime timestamp;
    private String type;
    private String title;
    private String detail;
    private String userMessage;
    private List<ObjectDetails> objects;

    @Getter
    @Builder
    public static class ObjectDetails {
        private String name;
        private String userMessage;
    }


}
