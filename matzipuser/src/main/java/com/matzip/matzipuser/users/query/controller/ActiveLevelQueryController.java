package com.matzip.matzipuser.users.query.controller;

import com.matzip.matzipuser.responsemessage.ResponseMessage;
import com.matzip.matzipuser.users.query.dto.ActiveLevelDTO;
import com.matzip.matzipuser.users.query.dto.ActiveLevelResMessageDTO;
import com.matzip.matzipuser.users.query.service.ActiveLevelQueryService;
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
public class ActiveLevelQueryController {

    private final ActiveLevelQueryService activeLevelQueryService;


    // 활동 등급 테이블 전체 조회 (관리자)
    @GetMapping("/active-level")
    public ResponseEntity<ActiveLevelResMessageDTO> searchAllActiveLevel(@RequestParam(value = "page", defaultValue = "1")long page) {
        List<ActiveLevelDTO> activeLevelList = activeLevelQueryService.searchAllActiveLevel(page);

        return ResponseEntity.ok(new ActiveLevelResMessageDTO(HttpStatus.OK.value(), ResponseMessage.FOUND.getMessage(), activeLevelList));
    }


}
