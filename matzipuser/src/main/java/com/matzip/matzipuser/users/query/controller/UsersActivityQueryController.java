package com.matzip.matzipuser.users.query.controller;

import com.matzip.matzipuser.responsemessage.ResponseMessage;
import com.matzip.matzipuser.users.query.dto.UsersActivityDTO;
import com.matzip.matzipuser.users.query.dto.UsersActivityQueryResMessageDTO;
import com.matzip.matzipuser.users.query.service.UsersActivityQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/user/api/v1")
@Tag(name = "User Activity", description = "회원 활동")
public class UsersActivityQueryController {

    private final UsersActivityQueryService usersActivityQueryService;

    // 전체 회원 활동 등급 조회 (관리자)
    @GetMapping("/users/activity")
    @Operation(summary = "전체 회원 활동 등급 조회", description = "전체 회원의 활동 등급을 조회한다.")
    public ResponseEntity<UsersActivityQueryResMessageDTO> searchAllUsersActivity(@RequestParam("page") int page, @RequestParam("active") String active) {

        List<UsersActivityDTO> usersActivityList = usersActivityQueryService.searchAllUsersActivity(page, active);

        return ResponseEntity.ok(new UsersActivityQueryResMessageDTO(HttpStatus.OK.value(), ResponseMessage.FOUND.getMessage(), usersActivityList));
    }
}
