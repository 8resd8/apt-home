package com.ssafy.home.auth.exception;

import com.ssafy.home.global.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(LoginFailedException.class)
    public ResponseEntity<String> handleLogin(LoginFailedException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<String> handleDuplicate(DuplicateException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleEmailVerifyFailException(EmailVerifyFailException e, WebRequest request) {
        return ErrorResponse.toResponseEntity(HttpStatus.UNAUTHORIZED, e.getMessage(), request.getDescription(false).replace("uri=", ""));
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleFileUploadException(FileUploadException e, WebRequest request) {
        return ErrorResponse.toResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), request.getDescription(false).replace("uri=", ""));
    }

}
