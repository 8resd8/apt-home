package com.ssafy.home.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = { CustomException.class })
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        return ErrorResponse.toResponseEntity(e.getStatus(), e.getMessage());
    }

    @ExceptionHandler(value = {NoSuchElementException.class})
    protected ResponseEntity<ErrorResponse> handleNoSuchElementException(NoSuchElementException e) {
        return ErrorResponse.toResponseEntity(HttpStatus.NOT_FOUND, e.getMessage());
    }
}
