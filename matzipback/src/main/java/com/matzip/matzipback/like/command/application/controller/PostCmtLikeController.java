package com.matzip.matzipback.like.command.application.controller;

import com.matzip.matzipback.like.command.application.service.PostCmtLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostCmtLikeController {

    private final PostCmtLikeService postCmtLikeService;

    // 게시글 댓글 좋아요
    @PostMapping("/{postSeq}/postcomment/{postcommentSeq}/like")
    public ResponseEntity<Long> toggleLike(
            @PathVariable Long postSeq,
            @PathVariable Long postCommentSeq
    ) {
        postCmtLikeService.active(postCommentSeq);

        return ResponseEntity.ok(postSeq);
    }

}
