package com.matzip.matzipuser.users.query.controller;

import com.matzip.matzipuser.responsemessage.ResponseMessage;
import com.matzip.matzipuser.users.query.dto.FollowQueryResMessageDTO;
import com.matzip.matzipuser.users.query.dto.FollowingUsersDTO;
import com.matzip.matzipuser.users.query.service.UsersFollowQueryService;
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
@Tag(name = "Follow", description = "팔로우")
public class UsersFollowQueryController {

    private final UsersFollowQueryService usersFollowQueryService;

    // 내가 팔로우한 유저 조회 기능
    @GetMapping("/follow")
    @Operation(summary = "나의 팔로잉 목록 조회", description = "내가 팔로우 한 유저 목록을 조회한다.")
    public ResponseEntity<FollowQueryResMessageDTO> searchFollowing(@RequestParam("userSeq") long userSeq, @RequestParam("page") long page) {
        List<FollowingUsersDTO> followList = usersFollowQueryService.searchFollowingUsers(userSeq, page);

        return ResponseEntity.ok(new FollowQueryResMessageDTO(HttpStatus.OK.value(), ResponseMessage.FOUND.getMessage(), followList));
    }

    // 팔로우한 유저 조회 기능
    @GetMapping("/follower")
    @Operation(summary = "나의 팔로우 목록 조회", description = "나를 팔로우 한 유저 목록을 조회한다.")
    public ResponseEntity<FollowQueryResMessageDTO> searchFollowers(@RequestParam("userSeq") long userSeq, @RequestParam("page") long page) {
        List<FollowingUsersDTO> followerList = usersFollowQueryService.searchFollowerUsers(userSeq, page);

        return ResponseEntity.ok(new FollowQueryResMessageDTO(HttpStatus.OK.value(), ResponseMessage.FOUND.getMessage(), followerList));
    }
}
