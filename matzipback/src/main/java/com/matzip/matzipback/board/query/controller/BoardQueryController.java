package com.matzip.matzipback.board.query.controller;

import com.matzip.matzipback.board.query.dto.BoardCategoryDTO;
import com.matzip.matzipback.board.query.service.BoardQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Tag(name = "Board", description = "게시판")
public class BoardQueryController {

    private final BoardQueryService boardQueryService;

    @GetMapping("/board")
    @Operation(summary = "게시판 카테고리 조회", description = "게시판 카테고리를 조회한다.")
    public ResponseEntity<List<BoardCategoryDTO>> getBoardCategories() {
        return ResponseEntity.ok().body(boardQueryService.getBoardCategories());
    }

    @GetMapping("/{userSeq}/board/like")
    @Operation(summary = "즐겨찾기 한 게시판 목록 조회", description = "즐겨찾기 한 게시판 목록을 조회한다.")
    public ResponseEntity<List<BoardCategoryDTO>> getBoardFavorCategories(@PathVariable("userSeq") String userSeq) {
        return ResponseEntity.ok().body(boardQueryService.getBoardFavorCategories(userSeq));
    }

}
