package com.ssafy.home.global.exception;

import com.ssafy.home.email.exception.CannotVerifyException;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {CustomException.class})
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e, WebRequest request) {
        return ErrorResponse.toResponseEntity(e.getStatus(), e.getMessage(), request.getDescription(false).replace("uri=", ""));
    }

    @ExceptionHandler(value = {NoSuchElementException.class})
    protected ResponseEntity<ErrorResponse> handleNoSuchElementException(NoSuchElementException e, WebRequest request) {
        return ErrorResponse.toResponseEntity(HttpStatus.NOT_FOUND, e.getMessage(), request.getDescription(false).replace("uri=", ""));
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleCannotVerifyException(CannotVerifyException e, WebRequest request) {
        return ErrorResponse.toResponseEntity(HttpStatus.BAD_REQUEST, e.getMessage(), request.getDescription(false).replace("uri=", ""));
    }
}
