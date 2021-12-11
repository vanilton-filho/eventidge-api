package com.eventidge.eventidgeapi.core.validation;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;


public class FileContentTypeValidator implements ConstraintValidator<FileContentType, MultipartFile> {

    // example: image/jpeg, image/png, application/pdf
    private List<String> allowedContentTypes;


    @Override
    public void initialize(FileContentType constraintAnnotation) {
        this.allowedContentTypes = Arrays.asList(constraintAnnotation.allowed());
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        return value == null || this.allowedContentTypes.contains(value.getContentType());
    }

}
