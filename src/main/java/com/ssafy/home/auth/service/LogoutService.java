package com.ssafy.home.auth.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService {

    private final HttpSession session;

    public void logout() {
        session.invalidate();
    }
}
