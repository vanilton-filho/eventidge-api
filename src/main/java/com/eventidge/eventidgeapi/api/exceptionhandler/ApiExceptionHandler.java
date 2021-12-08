package com.eventidge.eventidgeapi.api.exceptionhandler;

import com.eventidge.eventidgeapi.core.validation.CustomValidationException;
import com.eventidge.eventidgeapi.domain.exception.ConflictException;
import com.eventidge.eventidgeapi.domain.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFound(NotFoundException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String detail = ex.getMessage();

        ApiException apiException = createApiExceptionBuilder(status, "Not Found", detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, apiException, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<?> handleConflict(ConflictException ex, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        String detail = ex.getMessage();

        ApiException apiException = createApiExceptionBuilder(status, "CONFLICT", detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, apiException, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<?> handleValidation(CustomValidationException ex, WebRequest request) {
        return handleValidationInternal(ex, ex.getBindingResult(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleValidationInternal(ex, ex.getBindingResult(), headers, status, request);
    }

    private ResponseEntity<Object> handleValidationInternal(Exception ex, BindingResult bindingResult, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String detail = "One or more fields are invalid";

        List<ApiException.ObjectDetails> objectDetails = bindingResult.getAllErrors()
                .stream()
                .map(field -> {
                    String message = messageSource.getMessage(field, LocaleContextHolder.getLocale());
                    String name = field.getObjectName();
                    if (field instanceof FieldError) {
                        name = ((FieldError) field).getField();
                    }

                    return ApiException.ObjectDetails.builder()
                            .name(name)
                            .userMessage(message)
                            .build();
                }).collect(Collectors.toList());

        ApiException apiException = createApiExceptionBuilder(status, "INVALID DATA", detail)
                .userMessage(detail)
                .objects(objectDetails)
                .build();
        return handleExceptionInternal(ex, apiException, headers, status, request);
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

    private ApiException.ApiExceptionBuilder createApiExceptionBuilder(HttpStatus status, String exceptionType, String detail) {
        return ApiException.builder()
                .status(status.value())
                .type(exceptionType)
                .title(exceptionType)
                .detail(detail)
                .timestamp(OffsetDateTime.now());
    }
}
