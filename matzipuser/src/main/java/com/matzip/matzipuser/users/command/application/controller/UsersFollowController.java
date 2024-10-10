package com.matzip.matzipuser.users.command.application.controller;

import com.matzip.matzipuser.responsemessage.ResponseMessage;
import com.matzip.matzipuser.users.command.application.service.UsersFollowService;
import com.matzip.matzipuser.users.command.application.dto.FollowResMessageDTO;
import com.matzip.matzipuser.users.command.application.dto.FollowDTO;
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
@RequiredArgsConstructor
@RequestMapping("/user/api/v1/follow")
@Tag(name = "Follow", description = "팔로우")
public class UsersFollowController {

    private final UsersFollowService usersFollowService;
    
    // 팔로우 버튼을 누르는 기능
    // 팔로우가 되어 있으면 팔로우 취소됨
    @PostMapping
    @Operation(summary = "팔로우 신청/취소", description = "팔로우를 신청 혹은 취소한다.")
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
