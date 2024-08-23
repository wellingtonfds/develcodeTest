package com.checkout.develcode.exceptions.converters;

import org.springframework.validation.FieldError;

import com.checkout.develcode.exceptions.dtos.ValidateErrosDataDto;


public class FieldErrorConverter {

    public static final ValidateErrosDataDto fieldErrorToValidateErrosDataDto(
            FieldError fieldError) {
        return new ValidateErrosDataDto(fieldError.getField(), fieldError.getDefaultMessage());
    }
}
