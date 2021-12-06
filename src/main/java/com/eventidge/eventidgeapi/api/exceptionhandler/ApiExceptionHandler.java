package com.eventidge.eventidgeapi.api.exceptionhandler;

import com.eventidge.eventidgeapi.domain.exception.NotFoundException;
import com.eventidge.eventidgeapi.domain.exception.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFound(NotFoundException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String detail = ex.getMessage();

        ApiException apiException = createApiExceptionBuilder(status, "Not Found", detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, apiException, new HttpHeaders(), status, request);
    }

    private ApiException.ApiExceptionBuilder createApiExceptionBuilder(HttpStatus status, String exceptionType, String detail) {
        return ApiException.builder()
                .status(status.value())
                .type(exceptionType)
                .title(exceptionType)
                .detail(detail)
                .timestamp(OffsetDateTime.now());
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (body == null) {
            body = ApiException.builder()
                    .timestamp(OffsetDateTime.now())
                    .title(status.getReasonPhrase())
                    .status(status.value())
                    .userMessage("Internal Server Error")
                    .build();
        } else if (body instanceof String) {
            body = ApiException.builder()
                    .timestamp(OffsetDateTime.now())
                    .title((String) body)
                    .status(status.value())
                    .userMessage("Internal Server Error")
                    .build();
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }
}
