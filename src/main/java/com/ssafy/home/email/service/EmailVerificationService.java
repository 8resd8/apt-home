package com.ssafy.home.email.service;

import com.ssafy.home.email.dto.EmailCodeRequest;
import com.ssafy.home.email.exception.CannotVerifyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Duration;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailVerificationService {

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom random = new SecureRandom();
    private static final long EXPIRATION_TIME = 5;  // 인증 코드의 만료 시간 (5분)
    private final StringRedisTemplate redisTemplate;


    public String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }

    public void storeCode(String email, String authCode) {
        redisTemplate.opsForValue().set(email, authCode, Duration.ofMinutes(EXPIRATION_TIME));
    }

    public boolean verifyCode(String email, String authCode) {
        String storedAuthCode = redisTemplate.opsForValue().get(email);
        if (storedAuthCode != null && !storedAuthCode.equals(authCode)) {
            log.info("인증 실패: 이메일: {}, 저장된 코드: {}, 입력된 코드: {}", email, storedAuthCode, authCode);
            return false;
        }

        redisTemplate.delete(email);
        return true;
    }

    public void handleVerify(EmailCodeRequest request) {
        boolean isSuccess = verifyCode(request.email(), request.authCode());

        if (!isSuccess) {
            throw new CannotVerifyException();
        }
    }
}
