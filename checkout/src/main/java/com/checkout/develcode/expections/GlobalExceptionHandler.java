package com.checkout.develcode.expections;


import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.checkout.develcode.expections.converters.FieldErrorConverter;
import com.checkout.develcode.expections.dtos.ValidateErrosDataDto;



@RestControllerAdvice
@Hidden
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidateErrosDataDto>> error400(
            MethodArgumentNotValidException exception) {

        var errors = exception.getFieldErrors().stream()
                .map(error -> FieldErrorConverter.fieldErrorToValidateErrosDataDto(error)).toList();
        return ResponseEntity.badRequest().body(errors);
    }


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleEntityNotFoundException(
            EntityNotFoundException ex) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.NOT_FOUND.value(),
                ex.getMessage(), System.currentTimeMillis());
        logger.error("EntityNotFoundException: {}", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(Exception ex) {
        ApiErrorResponse errorResponse =
                new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "Ocorreu um erro inesperado. Por favor, tente novamente mais tarde.",
                        System.currentTimeMillis());
        logger.error("Exception: {}", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(CustomFeignException.class)
    public ResponseEntity<ApiErrorResponse> handleFeignException(CustomFeignException ex) {

        ApiErrorResponse errorResponse = new ApiErrorResponse(ex.getStatusCode(), ex.getMessage(),
                System.currentTimeMillis());
        logger.error("Exception: {}", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


