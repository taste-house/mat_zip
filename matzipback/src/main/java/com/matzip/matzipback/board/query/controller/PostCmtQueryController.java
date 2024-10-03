package com.matzip.matzipback.board.query.controller;

import com.matzip.matzipback.board.query.dto.PostCmtListResponseDTO;
import com.matzip.matzipback.board.query.service.PostCmtQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Tag(name = "Post Comment", description = "게시글 댓글")
public class PostCmtQueryController {

    private final PostCmtQueryService postCmtQueryService;

    @GetMapping("/users/{userSeq}/comments")
    @Operation(summary = "게시글 댓글 목록 조회", description = "회원이 작성한 게시글 댓글 목록을 조회한다.")
    public ResponseEntity<PostCmtListResponseDTO> getCommentsByUserSeq(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam Long userSeq
    ) {
        return ResponseEntity.ok().body(postCmtQueryService.getCommentsByUserSeq(page, size, userSeq));
    }
}
