package com.eventidge.eventidgeapi.core.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.BindingResult;

@AllArgsConstructor
@Getter
public class CustomValidationException extends RuntimeException {

    private BindingResult bindingResult;

}
