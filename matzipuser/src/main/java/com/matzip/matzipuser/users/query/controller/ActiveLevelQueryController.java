package com.matzip.matzipuser.users.query.controller;

import com.matzip.matzipuser.responsemessage.ResponseMessage;
import com.matzip.matzipuser.users.query.dto.ActiveLevelDTO;
import com.matzip.matzipuser.users.query.dto.ActiveLevelResMessageDTO;
import com.matzip.matzipuser.users.query.service.ActiveLevelQueryService;
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
@RequestMapping("/back/api/vi")
@Tag(name = "User Activity", description = "회원 활동")
public class ActiveLevelQueryController {

    private final ActiveLevelQueryService activeLevelQueryService;


    // 활동 등급 테이블 전체 조회 (관리자)
    @Operation(summary = "활동 등급 조회", description = "활동 등급을 조회한다.")
    @GetMapping("/active-level")
    public ResponseEntity<ActiveLevelResMessageDTO> searchAllActiveLevel(@RequestParam(value = "page", defaultValue = "1")long page) {
        List<ActiveLevelDTO> activeLevelList = activeLevelQueryService.searchAllActiveLevel(page);

        return ResponseEntity.ok(new ActiveLevelResMessageDTO(HttpStatus.OK.value(), ResponseMessage.FOUND.getMessage(), activeLevelList));
    }


}
