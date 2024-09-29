package com.matzip.matzipback.users.query.controller;

import com.matzip.matzipback.responsemessage.ResponseMessage;
import com.matzip.matzipback.users.query.dto.FollowQueryResMessageDTO;
import com.matzip.matzipback.users.query.dto.FollowingUsersDTO;
import com.matzip.matzipback.users.query.service.UsersFollowQueryService;
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
@RequestMapping("/api/v1")
public class UsersFollowQueryController {

    private final UsersFollowQueryService usersFollowQueryService;

    // 내가 팔로우한 유저 조회 기능
    @GetMapping("/follow")
    public ResponseEntity<FollowQueryResMessageDTO> searchFollowing(@RequestParam("userSeq") long userSeq, @RequestParam("page") long page) {
        List<FollowingUsersDTO> followList = usersFollowQueryService.searchFollowingUsers(userSeq, page);

        return ResponseEntity.ok(new FollowQueryResMessageDTO(HttpStatus.OK.value(), ResponseMessage.FOUND.getMessage(), followList));
    }

    // 팔로우한 유저 조회 기능
    @GetMapping("/follower")
    public ResponseEntity<FollowQueryResMessageDTO> searchFollowers(@RequestParam("userSeq") long userSeq, @RequestParam("page") long page) {
        List<FollowingUsersDTO> followerList = usersFollowQueryService.searchFollowerUsers(userSeq, page);

        return ResponseEntity.ok(new FollowQueryResMessageDTO(HttpStatus.OK.value(), ResponseMessage.FOUND.getMessage(), followerList));
    }
}
