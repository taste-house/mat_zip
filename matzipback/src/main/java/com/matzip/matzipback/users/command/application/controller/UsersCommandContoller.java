package com.matzip.matzipback.users.command.application.controller;

import com.matzip.matzipback.users.command.application.service.UsersCommandService;
import com.matzip.matzipback.users.command.dto.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UsersCommandContoller {

    private final UsersCommandService UsersCommandService;

    /* 회원가입 기능*/
    @PostMapping("/auth/register")
    public ResponseEntity<Void> createUser(@RequestBody CreateUserRequest newUser) {

        UsersCommandService.createUser(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
