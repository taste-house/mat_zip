package com.matzip.matzipback.users.command.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final Map<String, String> verificationCodes = new HashMap<>();  // 인증 코드 저장
    private final Map<String, Boolean> emailVerifiedMap = new HashMap<>(); // 인증 성공 상태 저장

    public String getVerificationCode(String email) {   // 테스트를 위해 필요
        return verificationCodes.get(email);
    }

    // 회원가입 인증코드 보내기
    public void sendSignUpEmail(String email, String name){
        String subject = "[맛zip]회원가입 인증코드입니다.";
        String verificationCode = makeVerificationCode();
        verificationCodes.put(email, verificationCode);

        SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject(subject);
            String emailContent = String.format(
                    "%s님 안녕하세요.\n\n회원가입을 해주셔서 감사합니다.\n\n" +
                            "인증코드는 %s입니다.\n\n" +
                            "이 메일은 회신이 불가능한 메일입니다.",
                    name, verificationCode
            );
            message.setText(emailContent);

        try {
            mailSender.send(message);
            log.info("회원가입 인증코드 이메일 발송 성공! 이메일: {}", email);
        } catch (Exception e) {
            log.error("회원가입 인증코드 이메일 발송 실패! 이메일: {}", email, e);
            throw new RuntimeException("메일 발송 실패", e);
        }
    }

    // 인증코드 생성
    private String makeVerificationCode(){
        return UUID.randomUUID().toString().substring(0,6).toUpperCase();
    }

    // 인증코드 확인
    public boolean verifyEmailCode(String email, String code) {
        String storedCode = verificationCodes.get(email);
        if (storedCode != null && storedCode.equals(code)) {
            log.info("이메일 인증 성공! 이메일: {}", email);
            emailVerifiedMap.put(email, true); // 인증 성공 상태 저장
            return true;
        } else {
            log.info("이메일 인증 실패! 이메일: {}, 입력된 코드: {}", email, code);
            return false;
        }
    }

    // 이메일 인증 여부 확인
    public boolean isEmailVerified(String email) {
        // emailVerifiedMap에 해당 이메일이 있으면(인증된 이메일이면) 그 값을 반환하고, 없으면 false 반환
        return emailVerifiedMap.getOrDefault(email, false); // 인증 여부 확인
    }

    // 인증이 완료되면 호출하여 인증 상태 삭제
    public void clearEmailVerificationStatus(String email) {
        verificationCodes.remove(email); // 인증 성공 시 맵에서 코드 제거
        emailVerifiedMap.remove(email); // 인증 성공 시 맵에서 인증여부 제거
    }

}
