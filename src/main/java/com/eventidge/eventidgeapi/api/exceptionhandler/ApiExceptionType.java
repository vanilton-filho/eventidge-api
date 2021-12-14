package com.eventidge.eventidgeapi.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ApiExceptionType {

    RESOURCE_NOT_FOUND("Resource not found"),
    RESOURCE_CONFLICT("Resource is in use"),
    BUSINESS_ERROR("Business error"),
    INVALID_DATA("Invalid data"),
    INVALID_PARAM("Invalid param"),
    MESSAGE_NOT_RECOGNIZED("Message not recognized"),
    INTERNAL_ERROR("Internal system error");

    private final String title;

    ApiExceptionType(String title) {
        this.title = title;
    }

}
