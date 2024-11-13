package com.ssafy.home.email.service;

import com.ssafy.home.email.dto.EmailRequest;
import com.ssafy.home.global.enums.Email;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class EmailSendService {

    private final JavaMailSender mailSender;
    private final EmailVerificationService emailVerificationService;

    private void sendEmailForm(String toEmail, String authCode) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;


        try {
            helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(toEmail); // 누구에게 보낼건지
            helper.setSubject(Email.TITLE.toString()); // 이메일 제목
            helper.setText(htmlContent(authCode), true); // 이메일 본문, true 설정시 html 처리된다

            mailSender.send(message);
            log.info("이메일 발송 성공: {}", message);
        } catch (MessagingException e) {
            log.error("이메일 발송 실패", e);
            throw new RuntimeException(e);
        }
    }

    public void handleEmailSend(EmailRequest requestEmail) {
        // 1. 인증코드 생성
        String authCode = emailVerificationService.generateVerificationCode();

        // 2. 인증코드 저장
        emailVerificationService.storeCode(requestEmail.email(), authCode);

        // 3. 이메일 전송
        sendEmailForm(requestEmail.email(), authCode);
    }


    private String htmlContent(String authCode) {
        return """
                <!DOCTYPE html>
                <html lang="ko">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                </head>
                <body style="font-family: Arial, sans-serif; background-color: #f4f4f9; margin: 0; padding: 0;">
                    <div style="max-width: 600px; margin: 0 auto; padding: 20px; background-color: #ffffff; border: 1px solid #dddddd; border-radius: 10px;">
                        <h2 style="color: #2c3e50;">SSAFY 이메일입니다</h2>
                        <p style="color: #34495e; line-height: 1.6;">SSAFY 12기 15반입니다</p>
                        <p style="color: #34495e; line-height: 1.6;">요청하신 이메일 인증 코드는 아래와 같습니다</p>
                        <br>
                        <p style="font-size: 24px; font-weight: bold; color: #e74c3c;">인증 코드: <strong>%s</strong></p>
                        <br>
                        <p style="color: #34495e; line-height: 1.6;">이 코드를 <strong>5분</strong> 이내에 입력해주세요.</p>
                        <p style="color: #34495e; line-height: 1.6;">만약 본인이 요청하지 않은 이메일이라면, 이 이메일을 무시하셔도 됩니다.</p>
                        <br>
                        <p style="color: #34495e; line-height: 1.6;">문의사항이 있으면 아래로 연락주세요:</p>
                        <p style="color: #34495e; line-height: 1.6;">SSAFY 12기 15반 김현우</p>
                        <p style="color: #34495e; line-height: 1.6;">위치: SSAFY 서울캠퍼스</p>
                        <p style="color: #34495e; line-height: 1.6;">주소: 서울특별시 강남구 테헤란로 212 멀티캠퍼스</p>
                        <br><br>
                        <p style="font-size: 12px; color: #7f8c8d;">이 이메일은 자동으로 발송된 이메일입니다.</p>
                    </div>
                </body>
                </html>
                """.formatted(authCode);

    }

}
