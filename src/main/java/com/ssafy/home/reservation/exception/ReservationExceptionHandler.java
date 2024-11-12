package com.ssafy.home.reservation.exception;

import com.ssafy.home.global.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ReservationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleNotFoundReservation(ReservationNotFoundException ex, WebRequest request) {
        return ErrorResponse.toResponseEntity(HttpStatus.NOT_FOUND, ex.getMessage(), request.getDescription(false).replace("uri=", ""));
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleStatusReservation(ReservationStatusException ex, WebRequest request) {
        return ErrorResponse.toResponseEntity(HttpStatus.NOT_FOUND, ex.getMessage(), request.getDescription(false).replace("uri=", ""));
    }
}
