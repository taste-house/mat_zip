package com.matzip.matzipback.board.command.application.controller;

import com.matzip.matzipback.board.command.application.dto.RequestPostCommentDTO;
import com.matzip.matzipback.board.command.application.dto.ResponsePostCommentDTO;
import com.matzip.matzipback.board.command.application.service.PostCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PostCommentController {

    private final PostCommentService postCommentService;

    // 댓글 등록
    @PostMapping("/postComment")
    public ResponseEntity<ResponsePostCommentDTO> createPostComment(@RequestBody RequestPostCommentDTO requestPostCommentDTO) {
        ResponsePostCommentDTO postComment = postCommentService.createPostComment(requestPostCommentDTO);

        return ResponseEntity.ok(postComment);
    }
}
