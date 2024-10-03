package com.matzip.matzipback.users.command.application.controller;

import com.matzip.matzipback.responsemessage.SuccessCode;
import com.matzip.matzipback.responsemessage.SuccessResMessage;
import com.matzip.matzipback.users.command.application.service.UsersCommandService;
import com.matzip.matzipback.users.command.dto.CreateUserRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Slf4j
@Tag(name = "Users", description = "회원관리")
public class UsersAuthController {

    private final UsersCommandService usersCommandService;

    @PostMapping("/logout")
    @Operation(summary = "로그아웃 요청", description = "클라이언트에게 토큰삭제를 요청한다.")
    public ResponseEntity<String> logout() {
        // 클라이언트에게 토큰 삭제 요청 안내
        log.info("POST /api/vi/auth/logout - 로그아웃 요청");
        return ResponseEntity.ok("로그아웃되었습니다. 클라이언트는 토큰을 삭제해야 합니다.");
    }

    // 1차 수정 완료 - 가람
    /* 회원가입 기능 */
    @PostMapping("/register")
    @Operation(summary = "회원가입", description = "이메일, 비밀번호와 이름, 휴대폰번호를 입력 후 회원가입이 가능하다.")
    public ResponseEntity<SuccessResMessage> createUser(@Valid @RequestBody CreateUserRequest newUser) {
        log.info("GET /api/v1/auth/register - 회원가입 요청 createUser: {}", newUser);
        usersCommandService.createUser(newUser);

        return ResponseEntity.ok(new SuccessResMessage(SuccessCode.BASIC_SAVE_SUCCESS));
    }
}
