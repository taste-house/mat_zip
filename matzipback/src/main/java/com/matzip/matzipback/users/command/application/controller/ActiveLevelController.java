package com.matzip.matzipback.users.command.application.controller;

import com.matzip.matzipback.responsemessage.ResponseMessage;
import com.matzip.matzipback.users.command.application.service.ActiveLevelService;
import com.matzip.matzipback.users.command.dto.ActiveLevelResDTO;
import com.matzip.matzipback.users.command.dto.CreateActiveLevelRequestDTO;
import com.matzip.matzipback.users.command.dto.CreateActiveLevelResMessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ActiveLevelController {

    private final ActiveLevelService activeLevelService;

    // active-level 저장
    @PostMapping("/active-level")
    public ResponseEntity<CreateActiveLevelResMessageDTO> saveActiveLevel(@RequestBody CreateActiveLevelRequestDTO createActiveLevelRequestDTO) {
        ActiveLevelResDTO savedActiveLevel = activeLevelService.saveActiveLevel(createActiveLevelRequestDTO);

        // 저장 실패
        if (savedActiveLevel == null) {
            return ResponseEntity.ok(new CreateActiveLevelResMessageDTO(HttpStatus.OK.value(), ResponseMessage.SAVE_FAIL.getMessage(), null));
        }

        // 저장 성공
        return ResponseEntity.ok(new CreateActiveLevelResMessageDTO(HttpStatus.OK.value(), ResponseMessage.SAVE_SUCCESS.getMessage(), List.of(savedActiveLevel)));
    }
}
