package com.ssafy.home.email.controller;

import com.ssafy.home.email.dto.EmailCodeRequest;
import com.ssafy.home.email.dto.EmailRequest;
import com.ssafy.home.email.service.EmailSendService;
import com.ssafy.home.email.service.EmailVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailController {
    private final EmailSendService emailSendService;
    private final EmailVerificationService emailVerificationService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void sendCodeToEmail(@Validated @RequestBody EmailRequest email) {
        emailSendService.handleEmailSend(email);
    }

    @PostMapping("/code")
    @ResponseStatus(HttpStatus.OK)
    public void authCodeVerification(@Validated @RequestBody EmailCodeRequest emailCode) {
        emailVerificationService.handleVerify(emailCode);
    }
}
