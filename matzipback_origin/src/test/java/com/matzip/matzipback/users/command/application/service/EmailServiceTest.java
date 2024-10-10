package com.matzip.matzipback.users.command.application.service;

import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
//@TestPropertySource("/application-test.yml")
class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @MockBean
    private JavaMailSender mailSender; // 실제 메일 발송을 Mock 처리

    @DisplayName("회원가입 인증코드 이메일 발송 테스트")
    @Test
    void sendSignUpEmail() {
        // given
        String email = "matzip@gmail.com";
        String name = "TestUser";

        // when
        emailService.sendSignUpEmail(email, name);

        // then
        Mockito.verify(mailSender, Mockito.times(1)).send(any(SimpleMailMessage.class));  // 메일 발송 메소드가 1회 호출되었는지 확인
        assertFalse(emailService.isEmailVerified(email));  // 인증 상태가 null이 아닌지 확인 (인증 전이므로 false여야 함)
        assertFalse(emailService.isEmailVerified(email));  // 인증되기 전이므로 false
    }

    @DisplayName("이메일 인증 성공")
    @Test
    void verifyEmailCode_Success() {
        // given
        String email = "test@gmail.com";
        String name = "TestUser";
        emailService.sendSignUpEmail(email, name); // 인증코드가 생성됨
        String code = emailService.getVerificationCode(email); // 생성된 인증코드 가져오기

        // when
        boolean isVerified = emailService.verifyEmailCode(email, code);

        // then
        assertTrue(isVerified, "이메일 인증 성공");
    }

    @DisplayName("잘못된 코드로 이메일 인증 실패")
    @Test
    void verifyEmailCode_Fail() {
        // given
        String email = "test@gmail.com";
        String name = "TestUser";
        emailService.sendSignUpEmail(email, name); // 인증코드가 생성됨
        String code = "ABV123"; // 인증코드 임의 생성

        // when
        boolean isVerified = emailService.verifyEmailCode(email, code);

        // then
        assertFalse(isVerified, "이메일 인증 실패");
    }

    @DisplayName("이메일 인증 여부 확인")
    @Test
    void isEmailVerified() {
        // given
        String email = "test@gmail.com";
        String name = "TestUser";
        emailService.sendSignUpEmail(email, name); // 인증코드가 생성됨
        String code = emailService.getVerificationCode(email); // 생성된 인증코드 가져오기
        System.out.println("Generated Code: " + code);
        boolean verifyResult  = emailService.verifyEmailCode(email, code);  // 인증코드 확인
        System.out.println("Verify Result: " + verifyResult);

        // when
        boolean isVerifiedEmail = emailService.isEmailVerified(email);

        // then
        assertTrue(isVerifiedEmail, "이메일 인증 성공");
    }

    @DisplayName("이메일 인증 상태 삭제")
    @Test
    void clearEmailVerificationStatus() {
        // given
        String email = "test@gmail.com";
        String name = "TestUser";
        emailService.sendSignUpEmail(email, name); // 인증코드가 생성됨
        String code = emailService.getVerificationCode(email); // 생성된 인증코드 가져오기
        System.out.println("Generated Code: " + code);
        boolean verifyResult  = emailService.verifyEmailCode(email, code);  // 인증코드 확인
        System.out.println("Verify Result: " + verifyResult);
        boolean isVerifiedEmail = emailService.isEmailVerified(email);
        assertTrue(isVerifiedEmail, "이메일 인증 성공");
        emailService.clearEmailVerificationStatus(email);   // 인증 상태 삭제

        // when
        // 인증 상태가 삭제되었는지 확인
        boolean isVerifiedAfterClear = emailService.isEmailVerified(email);

        // then
        assertFalse(isVerifiedAfterClear, "인증 상태 삭제 확인");
    }

}