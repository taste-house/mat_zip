package com.matzip.matzipuser.users.command.application.controller;

import com.matzip.matzipuser.exception.ErrorCode;
import com.matzip.matzipuser.exception.RestApiException;
import com.matzip.matzipuser.responsemessage.ResponseMessage;
import com.matzip.matzipuser.responsemessage.SuccessCode;
import com.matzip.matzipuser.responsemessage.SuccessResMessage;
import com.matzip.matzipuser.users.command.application.dto.*;
import com.matzip.matzipuser.users.command.application.service.ActiveLevelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/api/v1")
@Tag(name = "User Activity", description = "회원 활동")
public class ActiveLevelController {

    private final ActiveLevelService activeLevelService;

    // active-level 저장
    @PostMapping("/active-level")
    @Operation(summary = "회원 활동 등급 등록", description = "회원 활동 등급을 등록한다.")
    public ResponseEntity<SuccessResMessage> saveActiveLevel(@RequestBody CreateActiveLevelRequestDTO createActiveLevelRequestDTO) {
        boolean result = activeLevelService.saveActiveLevel(createActiveLevelRequestDTO);

        // 저장 실패
        if (!result) {
            throw new RestApiException(ErrorCode.NOT_SAVED);
        }

        // 저장 성공
        return ResponseEntity.ok(new SuccessResMessage(SuccessCode.BASIC_SAVE_SUCCESS));
    }

    @PutMapping("/active-level")
    @Operation(summary = "회원 활동 등급 수정", description = "회원 활동 등급을 수정한다.")
    public ResponseEntity<SuccessResMessage> updateActiveLevel(
            @RequestBody UpdateActiveLevelDTO updateActiveLevelDTO) {

        activeLevelService.updateActiveLevel(updateActiveLevelDTO);

        return ResponseEntity.ok(new SuccessResMessage(SuccessCode.BASIC_UPDATE_SUCCESS));
    }

    @DeleteMapping("/active-level/{activeLevelSeq}")
    @Operation(summary = "회원 활동 등급을 삭제", description = "회원 활동 등급을 삭제한다.")
    public ResponseEntity<SuccessResMessage> deleteActiveLevel(@PathVariable Long activeLevelSeq) {

        activeLevelService.deleteActiveLevel(activeLevelSeq);
        return ResponseEntity.ok(new SuccessResMessage(SuccessCode.BASIC_DELETE_SUCCESS));
    }

    // userActivity point 업데이트
    @PutMapping("/user-activity/point")
    @Operation(summary = "회원 활동 포인트 업데이트", description = "회원 활동 포인트를 업데이트한다.")
    public ResponseEntity<Void> updateUserActivityPoint(
            @RequestBody UpdateUserActivityPointDTO updateUserActivityPointDTO) {

        activeLevelService.updateUserPoint(updateUserActivityPointDTO);
        return ResponseEntity.ok().build();
    }
}
