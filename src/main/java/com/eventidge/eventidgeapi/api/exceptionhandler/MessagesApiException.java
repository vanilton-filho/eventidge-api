package com.eventidge.eventidgeapi.api.exceptionhandler;

public class MessagesApiException {

    public static final String GENERIC_ERROR = "An unexpected internal system error has occurred. " +
            "Please try again and if the problem persists contact your system administrator.";

    public static final String BUSINESS_ERROR = "You violated a business rule";

    public static final String PROPERTY_BIDING = "Property %s does not exist. " +
            "Please correct or remove this property and try again.";

    public static final String INVALID_FORMAT = "Property '%s' received value '%s' which is of an invalid type. " +
            "Correct and enter a value compatible with type '%s'";

    public static final String RESOURCE_NOT_FOUND = "Resource %s you tried to access is non-existent.";

    public static final String INVALID_URL_FORMAT = "The URL parameter '%s' received the value '%s', which is an invalid type. Correct and enter a value compatible with type %s";

}
