package com.ssafy.home.auth.controller;

import com.ssafy.home.auth.dto.request.EmailCodeRequest;
import com.ssafy.home.auth.dto.request.EmailRequest;
import com.ssafy.home.auth.service.email.EmailSendService;
import com.ssafy.home.auth.service.email.EmailVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/email")
@RequiredArgsConstructor
public class AuthEmailController {
    private final EmailSendService emailSendService;
    private final EmailVerificationService emailVerificationService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void sendCodeToEmail(@Validated @RequestBody EmailRequest email) {
        emailSendService.handleEmailSend(email);
    }

    @PostMapping("/code")
    @ResponseStatus(HttpStatus.OK)
    public void authCodeEmail(@Validated @RequestBody EmailCodeRequest emailCode) {
        emailVerificationService.handleVerify(emailCode);
    }
}
