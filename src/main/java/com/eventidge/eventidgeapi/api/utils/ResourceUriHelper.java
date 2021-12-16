package com.eventidge.eventidgeapi.api.utils;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@UtilityClass
public class ResourceUriHelper {

    public static String addUriResponseHeader(String resourcePath, Object resourceId) {
        URI uri = ServletUriComponentsBuilder.fromUriString(resourcePath)
                .path("/{id}")
                .buildAndExpand(resourceId)
                .toUri();
        HttpServletResponse response = ((ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes()).getResponse();

        response.setHeader(HttpHeaders.LOCATION, uri.toString());
        return uri.toString();
    }



}
