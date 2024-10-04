package com.matzip.matzipback.users.query.controller;

import com.matzip.matzipback.responsemessage.ResponseMessage;
import com.matzip.matzipback.users.query.dto.UsersActivityDTO;
import com.matzip.matzipback.users.query.dto.UsersActivityQueryResMessageDTO;
import com.matzip.matzipback.users.query.service.UsersActivityQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/back/api/vi")
public class UsersActivityQueryController {

    private final UsersActivityQueryService usersActivityQueryService;

    // 전체 회원 활동 등급 조회 (관리자)
    @GetMapping("/users/activity")
    public ResponseEntity<UsersActivityQueryResMessageDTO> searchAllUsersActivity(@RequestParam("page") int page, @RequestParam("active") String active) {

        List<UsersActivityDTO> usersActivityList = usersActivityQueryService.searchAllUsersActivity(page, active);

        return ResponseEntity.ok(new UsersActivityQueryResMessageDTO(HttpStatus.OK.value(), ResponseMessage.FOUND.getMessage(), usersActivityList));
    }
}
