package com.matzip.matzipback.users.command.application.controller;

import com.matzip.matzipback.users.command.application.service.EmailService;
import com.matzip.matzipback.users.command.dto.EmailChkRequest;
import com.matzip.matzipback.users.command.dto.EmailSendRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "Users", description = "회원관리")
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/auth/mail-verification")
    @Operation(summary = "이메일 인증", description = "입력받은 이메일로 인증코드를 발송한다.")
    public ResponseEntity<String> sendVerificationCode(@RequestBody EmailSendRequest request) {
        emailService.sendSignUpEmail(request.getUserEmail(), request.getUserName());

        return ResponseEntity.ok("인증 코드가 발송되었습니다.");
    }

    @PostMapping("/auth/chkEmailCode")
    @Operation(summary = "이메일 인증검증", description = "인증코드가 일치하는지 검증한다.")
    public ResponseEntity<String> checkVerifyCode(@RequestBody EmailChkRequest request) {
        boolean isVerified = emailService.verifyEmailCode(request.getUserEmail(), request.getVerificationCode());
        if (isVerified) {
            return ResponseEntity.ok("이메일 인증이 완료되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("인증 코드가 올바르지 않습니다.");
        }
    }

}
