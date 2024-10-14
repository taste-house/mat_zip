package com.matzip.matzipback.matzipList.query.controller;

import com.matzip.matzipback.common.util.CustomUserUtils;

import com.matzip.matzipback.matzipList.query.dto.ListContentDTO;

import com.matzip.matzipback.matzipList.query.dto.ListSearchAllDTO;
import com.matzip.matzipback.matzipList.query.dto.ListSearchUserDTO;
import com.matzip.matzipback.matzipList.query.service.ListQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/back/api/v1")
@Tag(name = "List", description = "리스트")
public class ListQueryController {

    private final ListQueryService listQueryService;

    // 유저 본인의 리스트 서랍 조회(모든 리스트 상태 조회)
    @GetMapping("/listbox")
    @Operation(summary = "본인 리스트 서랍 조회", description = "본인의 리스트 서랍을 조회한다.")
    public ResponseEntity<List<ListSearchAllDTO>> getListBox() {
        //URL에서 유저 시퀀스 받지 않고 로그인한 사용자의 유저 시퀀스를 받아오는 것으로 구현
        Long listUserSeq = CustomUserUtils.getCurrentUserSeq();
        return ResponseEntity.ok().body(listQueryService.getListBox(listUserSeq));
    }
    // 다른 유저의 리스트 서랍 조회
    @GetMapping("/listbox/{listUserSeq}")
    @Operation(summary = "다른 유저 리스트 서랍 조회", description = "다른 유저의 리스트 서랍을 조회한다.")
    public ResponseEntity<List<ListSearchUserDTO>> getUserListBox(@PathVariable("listUserSeq") Long listUserSeq) {
        return ResponseEntity.ok().body(listQueryService.getUserListBox(listUserSeq));
    }

    // 리스트 상세 조회
    @GetMapping("/listbox/list/{listSeq}")
    @Operation(summary = "리스트 상세 조회", description = "공개된 리스트를 상세 조회한다.")
    public ResponseEntity<List<ListContentDTO>> getListContents(@PathVariable("listSeq") Long listSeq) {
        return ResponseEntity.ok().body(listQueryService.getListContests(listSeq));
    }

}
