package com.matzip.matzipback.board.query.controller;

import com.matzip.matzipback.board.query.dto.BoardCategoryDTO;
import com.matzip.matzipback.board.query.service.BoardQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BoardQueryController {

    private final BoardQueryService boardQueryService;

    @GetMapping("/board")
    public ResponseEntity<List<BoardCategoryDTO>> getBoardCategories() {
        return ResponseEntity.ok().body(boardQueryService.getBoardCategories());
    }

}
