package com.matzip.matzipback.matzipList.query.controller;

import com.matzip.matzipback.board.query.controller.BoardQueryController;
import com.matzip.matzipback.common.util.CustomUserUtils;
import com.matzip.matzipback.matzipList.query.dto.ListCategoryDTO;
import com.matzip.matzipback.matzipList.query.dto.ListSearchAllDTO;
import com.matzip.matzipback.matzipList.query.service.ListQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ListQueryController {

    private final ListQueryService listQueryService;

    // 유저 본인의 리스트 서랍 조회(모든 리스트 상태 조회)
    @GetMapping("/listBox/{listUserSeq}")
    public ResponseEntity<List<ListSearchAllDTO>> getListBox() {
        Long listUserSeq = CustomUserUtils.getCurrentUserSeq();
        return ResponseEntity.ok().body(listQueryService.getListBox(listUserSeq));
    }



}
