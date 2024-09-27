package com.matzip.matzipback.users.command.application.controller;

import com.matzip.matzipback.responsemessage.ResponseMessage;
import com.matzip.matzipback.users.command.application.service.UsersFollowService;
import com.matzip.matzipback.users.command.dto.FollowResMessageDTO;
import com.matzip.matzipback.users.command.dto.FollowDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/follow")
public class UsersFollowController {

    private final UsersFollowService usersFollowService;
    
    // 팔로우 버튼을 누르는 기능
    // 팔로우가 되어 있으면 팔로우 취소됨
    @PostMapping
    public ResponseEntity<FollowResMessageDTO> doFollow(@RequestBody FollowDTO followDTO) {
        int result = usersFollowService.doFollow(followDTO);

        if (result == 0) { // 잘못된 요청
            return ResponseEntity.ok(new FollowResMessageDTO(HttpStatus.OK.value(), ResponseMessage.WRONG_REQUEST.getMessage()));
        } else if (result == 2) { // 팔로우 성공
            return ResponseEntity.ok(new FollowResMessageDTO(HttpStatus.OK.value(), ResponseMessage.FOLLOW_SUCCESS.getMessage()));
        }
        // 팔로우 취소
        return ResponseEntity.ok(new FollowResMessageDTO(HttpStatus.OK.value(), ResponseMessage.FOLLOW_DELETE.getMessage()));
    }

}
