package com.matzip.matzipuser.users.command.application.controller;

import com.matzip.matzipuser.common.util.CustomUserUtils;
import com.matzip.matzipuser.exception.ErrorCode;
import com.matzip.matzipuser.exception.RestApiException;
import com.matzip.matzipuser.responsemessage.SuccessCode;
import com.matzip.matzipuser.responsemessage.SuccessResMessage;
import com.matzip.matzipuser.users.command.application.service.UsersCommandService;
import com.matzip.matzipuser.users.command.application.dto.DeleteUserRequest;
import com.matzip.matzipuser.users.command.application.dto.UpdateUserRequest;
import com.matzip.matzipuser.users.command.application.dto.UpdateUserStatusDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/api/v1/users/list")
@Slf4j
@Tag(name = "Users", description = "회원관리")
public class UsersInfoController {

    private final UsersCommandService usersCommandService;

    // 1차 수정 완료 - 가람
    /* 회원정보 수정 */
    @PutMapping("/{userSeq}/edit")
    @Operation(summary = "회원정보 수정", description = "비밀번호와 휴대폰번호, 닉네임을 수정 가능하다.")
    public ResponseEntity<SuccessResMessage> updateUser(
            @PathVariable long userSeq,
            @Valid @RequestBody UpdateUserRequest updateUserInfo) {
        log.info("GET /user/api/v1/users/list/{userSeq}/edit - 회원정보 수정 요청 회원번호 : {}, updateUserInfo: {}", userSeq, updateUserInfo);

        // 현재 로그인한 유저의 userSeq를 가져옴
        long currentUserSeq = CustomUserUtils.getCurrentUserSeq();

        // 로그인한 유저의 userSeq와 요청의 userSeq가 다르면 403 Forbidden 응답
        if (currentUserSeq != userSeq) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
            throw new RestApiException(ErrorCode.FORBIDDEN_ACCESS);
        }

         usersCommandService.updateUserInfo(updateUserInfo);
        return ResponseEntity.ok(new SuccessResMessage(SuccessCode.BASIC_UPDATE_SUCCESS));
    }

    // 1차 수정 완료 - 가람
    /* 회원탈퇴 */
    @DeleteMapping("/{userSeq}/delete")
    @Operation(summary = "회원탈퇴", description = "비밀번호 검증 후 탈퇴를 할 수 있다.")
    public ResponseEntity<SuccessResMessage> deleteUser(@PathVariable long userSeq, @RequestBody DeleteUserRequest deleteUserInfo) {
        log.info("GET /user/api/v1users/list/{userSeq}/delete - 회원 탈퇴 요청 : {}, {}", userSeq, deleteUserInfo);

        // 현재 로그인한 유저의 userSeq를 가져옴
        long currentUserSeq = CustomUserUtils.getCurrentUserSeq();

        // 로그인한 유저의 userSeq와 요청의 userSeq가 다르면 403 Forbidden 응답
        if (currentUserSeq != userSeq) {
            throw new RestApiException(ErrorCode.FORBIDDEN_ACCESS);
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

        // 서비스에서 탈퇴 처리
        usersCommandService.deleteUser(deleteUserInfo);
        return ResponseEntity.ok(new SuccessResMessage(SuccessCode.USER_DELETION_SUCCESS));
//        return ResponseEntity.status(HttpStatus.OK).body("탈퇴가 성공적으로 처리되었습니다.");
    }


    // 회원 상태 변경
    @PutMapping("/userStatus")
    @Operation(summary = "회원 상태 변경", description = "회원의 상태를 변경한다.")
    public ResponseEntity<Void> updateUserStatus(@RequestBody UpdateUserStatusDTO updateUserStatusDTO) {

        usersCommandService.updateUserStatus(updateUserStatusDTO);

        return ResponseEntity.ok().build();
    }


}
