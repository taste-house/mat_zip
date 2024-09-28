package com.matzip.matzipback.users.query.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UsersQueryController {

    @GetMapping("/auth/register")
    public String getSignUp(){
        return "";
    }

    @GetMapping("/auth/login")
    public String getLogin(){
        return "";
    }


}
