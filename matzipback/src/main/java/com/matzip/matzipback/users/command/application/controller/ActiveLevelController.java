package com.matzip.matzipback.users.command.application.controller;

import com.matzip.matzipback.responsemessage.ResponseMessage;
import com.matzip.matzipback.users.command.application.service.ActiveLevelService;
import com.matzip.matzipback.users.command.dto.ActiveLevelResDTO;
import com.matzip.matzipback.users.command.dto.CreateActiveLevelReqDTO;
import com.matzip.matzipback.users.command.dto.ActiveLevelResMessageDTO;
import com.matzip.matzipback.users.command.dto.UpdateActiveLevelReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ActiveLevelController {

    private final ActiveLevelService activeLevelService;

    // active-level 저장
    @PostMapping("/active-level")
    public ResponseEntity<ActiveLevelResMessageDTO> saveActiveLevel(@RequestBody CreateActiveLevelReqDTO createActiveLevelReqDTO) {
        ActiveLevelResDTO savedActiveLevel = activeLevelService.saveActiveLevel(createActiveLevelReqDTO);

        // 저장 실패
        if (savedActiveLevel == null) {
            return ResponseEntity.ok(new ActiveLevelResMessageDTO(HttpStatus.OK.value(), ResponseMessage.SAVE_FAIL.getMessage(), null));
        }

        // 저장 성공
        return ResponseEntity.ok(new ActiveLevelResMessageDTO(HttpStatus.OK.value(), ResponseMessage.SAVE_SUCCESS.getMessage(), List.of(savedActiveLevel)));
    }

    // active-level 수정
    @PutMapping("/active-level")
    public ResponseEntity<ActiveLevelResMessageDTO> updateActiveLevel(@RequestBody UpdateActiveLevelReqDTO updateActiveLevelReqDTO) {
        ActiveLevelResDTO updatedActiveLevel = activeLevelService.updateActiveLevel(updateActiveLevelReqDTO);

        return ResponseEntity.ok(new ActiveLevelResMessageDTO(HttpStatus.OK.value(), ResponseMessage.UPDATE_SUCCESS.getMessage(), List.of(updatedActiveLevel)));
    }
}
