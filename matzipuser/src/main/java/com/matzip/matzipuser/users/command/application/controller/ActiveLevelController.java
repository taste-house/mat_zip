package com.matzip.matzipuser.users.command.application.controller;

import com.matzip.matzipuser.responsemessage.ResponseMessage;
import com.matzip.matzipuser.users.command.application.dto.ActiveLevelResDTO;
import com.matzip.matzipuser.users.command.application.dto.CreateActiveLevelRequestDTO;
import com.matzip.matzipuser.users.command.application.dto.CreateActiveLevelResMessageDTO;
import com.matzip.matzipuser.users.command.application.dto.UpdateUserActivityPointDTO;
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
    public ResponseEntity<CreateActiveLevelResMessageDTO> saveActiveLevel(@RequestBody CreateActiveLevelRequestDTO createActiveLevelRequestDTO) {
        ActiveLevelResDTO savedActiveLevel = activeLevelService.saveActiveLevel(createActiveLevelRequestDTO);

        // 저장 실패
        if (savedActiveLevel == null) {
            return ResponseEntity.ok(new CreateActiveLevelResMessageDTO(HttpStatus.OK.value(), ResponseMessage.SAVE_FAIL.getMessage(), null));
        }

        // 저장 성공
        return ResponseEntity.ok(new CreateActiveLevelResMessageDTO(HttpStatus.OK.value(), ResponseMessage.SAVE_SUCCESS.getMessage(), List.of(savedActiveLevel)));
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
