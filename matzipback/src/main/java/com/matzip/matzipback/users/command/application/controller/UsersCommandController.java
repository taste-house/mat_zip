package com.matzip.matzipback.users.command.application.controller;

import com.matzip.matzipback.users.command.application.service.UsersCommandService;
import com.matzip.matzipback.users.command.dto.CreateUserRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class UsersCommandController {

    private final UsersCommandService UsersCommandService;

    /* 회원가입 기능*/
    @PostMapping("/auth/register")
    public ResponseEntity<String> createUser(@Valid @RequestBody CreateUserRequest newUser, BindingResult bindingResult) {
        // 유효성 검사 오류가 있는 경우 처리
        if (bindingResult.hasErrors()) {
            // 첫 번째 오류 메시지를 반환
            String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.badRequest().body(errorMessage);
        }
        log.info("회원가입 요청 createUser: {}", newUser);
        UsersCommandService.createUser(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
