package com.ssafy.home.profile.member;

import com.ssafy.home.profile.member.exception.CannotUpdateMemberException;
import com.ssafy.home.profile.member.exception.NotFoundMemberException;
import com.ssafy.home.profile.member.exception.ValidPasswordException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProfileExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> handleCannotUpdateMember(CannotUpdateMemberException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleNotFoundMember(NotFoundMemberException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleValidPasswordMember(ValidPasswordException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
