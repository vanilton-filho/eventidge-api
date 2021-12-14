package com.eventidge.eventidgeapi.api.exceptionhandler;

import com.eventidge.eventidgeapi.core.validation.CustomValidationException;
import com.eventidge.eventidgeapi.domain.exception.BusinessException;
import com.eventidge.eventidgeapi.domain.exception.ConflictException;
import com.eventidge.eventidgeapi.domain.exception.NotFoundException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleUncaught(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiExceptionType type = ApiExceptionType.INTERNAL_ERROR;
        String detail = MessagesApiException.GENERIC_ERROR;

        log.error(ex.getMessage(), ex);

        ApiException apiException = createApiExceptionBuilder(status, type, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, apiException, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFound(NotFoundException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiExceptionType type = ApiExceptionType.RESOURCE_NOT_FOUND;
        String detail = ex.getMessage();

        ApiException apiException = createApiExceptionBuilder(status, type, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, apiException, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<?> handleConflict(ConflictException ex, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ApiExceptionType type = ApiExceptionType.RESOURCE_CONFLICT;
        String detail = ex.getMessage();

        ApiException apiException = createApiExceptionBuilder(status, type, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, apiException, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusiness(BusinessException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiExceptionType type = ApiExceptionType.BUSINESS_ERROR;
        String detail = ex.getMessage();

        ApiException apiException = createApiExceptionBuilder(status, type, detail)
                .userMessage(MessagesApiException.BUSINESS_ERROR)
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
        ApiExceptionType type = ApiExceptionType.INVALID_DATA;

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

        ApiException apiException = createApiExceptionBuilder(status, type, detail)
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
                    .userMessage(MessagesApiException.GENERIC_ERROR)
                    .build();
        } else if (body instanceof String) {
            body = ApiException.builder()
                    .timestamp(OffsetDateTime.now())
                    .title((String) body)
                    .status(status.value())
                    .userMessage(MessagesApiException.GENERIC_ERROR)
                    .build();
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private ApiException.ApiExceptionBuilder createApiExceptionBuilder(HttpStatus status, ApiExceptionType exceptionType, String detail) {
        return ApiException.builder()
                .status(status.value())
                .title(exceptionType.getTitle())
                .detail(detail)
                .timestamp(OffsetDateTime.now());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Throwable root = ExceptionUtils.getRootCause(ex);

        if (root instanceof InvalidFormatException) {
            return handleInvalidFormat((InvalidFormatException) root, headers, status, request);
        } else if (root instanceof PropertyBindingException) {
            return handlePropertyBinding((PropertyBindingException) root, headers, status, request);
        }

        ApiExceptionType type = ApiExceptionType.MESSAGE_NOT_RECOGNIZED;
        String detail = "The request body is invalid, check syntax error.";

        ApiException apiException = createApiExceptionBuilder(status, type, detail)
                .userMessage(MessagesApiException.GENERIC_ERROR)
                .build();
        return super.handleExceptionInternal(ex, apiException, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String detail = String.format(MessagesApiException.RESOURCE_NOT_FOUND, ex.getRequestURL());
        ApiExceptionType type = ApiExceptionType.RESOURCE_NOT_FOUND;

        ApiException apiException = createApiExceptionBuilder(status, type, detail)
                .userMessage(MessagesApiException.GENERIC_ERROR)
                .build();
        return handleExceptionInternal(ex, apiException, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (ex instanceof MethodArgumentTypeMismatchException) {
            return handleMethodArgumentTypeMismatchException((MethodArgumentTypeMismatchException) ex, headers, status, request);
        }

        return super.handleTypeMismatch(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.status(status).headers(headers).build();
    }

    private ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String detail = String.format(MessagesApiException.INVALID_URL_FORMAT, ex.getName(), ex.getValue(), Objects.requireNonNull(ex.getRequiredType()).getSimpleName());
        ApiExceptionType type = ApiExceptionType.INVALID_PARAM;
        ApiException apiException = createApiExceptionBuilder(status, type, detail)
                .userMessage(MessagesApiException.GENERIC_ERROR)
                .build();
        return handleExceptionInternal(ex, apiException, headers, status, request);
    }

    private ResponseEntity<Object> handleInvalidFormat(InvalidFormatException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String path = joinPath(ex.getPath());

        ApiExceptionType type = ApiExceptionType.MESSAGE_NOT_RECOGNIZED;
        String detail = String.format(
                MessagesApiException.INVALID_FORMAT, path, ex.getValue(),
                ex.getTargetType().getSimpleName());

        ApiException apiException = createApiExceptionBuilder(status, type, detail)
                .userMessage(MessagesApiException.GENERIC_ERROR)
                .build();

        return handleExceptionInternal(ex, apiException, headers, status, request);

    }

    private ResponseEntity<Object> handlePropertyBinding(PropertyBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String path = joinPath(ex.getPath());

        String detail = String.format(MessagesApiException.PROPERTY_BIDING, path);
        ApiExceptionType type = ApiExceptionType.MESSAGE_NOT_RECOGNIZED;
        ApiException apiException = createApiExceptionBuilder(status, type, detail)
                .userMessage(MessagesApiException.GENERIC_ERROR)
                .build();
        return handleExceptionInternal(ex, apiException, headers, status, request);
    }

    private String joinPath(List<JsonMappingException.Reference> references) {
        return references.stream()
                .map(JsonMappingException.Reference::getFieldName)
                .collect(Collectors.joining("."));
    }
}
