package com.eventidge.eventidgeapi.api.utils;

import lombok.experimental.UtilityClass;
import org.springframework.http.MediaType;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

import java.util.List;

@UtilityClass
public class MediaTypeHelper {

    public static void checkMediaTypes(MediaType mediaTypePhoto, List<MediaType> acceptedMediaTypes) throws HttpMediaTypeNotAcceptableException {
        boolean isEql = acceptedMediaTypes.stream()
                .anyMatch(mediaType -> mediaType.isCompatibleWith(mediaTypePhoto));
        if (!isEql) {
            throw new HttpMediaTypeNotAcceptableException(acceptedMediaTypes);
        }
    }

}
