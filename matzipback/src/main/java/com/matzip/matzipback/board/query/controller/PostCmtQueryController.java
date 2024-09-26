package com.matzip.matzipback.board.query.controller;

import com.matzip.matzipback.board.query.dto.PostCmtListResponseDTO;
import com.matzip.matzipback.board.query.service.PostCmtQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PostCmtQueryController {

    private final PostCmtQueryService postCmtQueryService;

    @GetMapping("/users/{userSeq}/comments")
    public ResponseEntity<PostCmtListResponseDTO> getCommentsByUserSeq(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam Long userSeq
    ) {
        return ResponseEntity.ok().body(postCmtQueryService.getCommentsByUserSeq(page, size, userSeq));
    }
}
