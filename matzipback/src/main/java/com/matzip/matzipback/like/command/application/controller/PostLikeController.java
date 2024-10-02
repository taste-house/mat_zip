package com.matzip.matzipback.like.command.application.controller;

import com.matzip.matzipback.like.command.application.dto.PostLikeReqDTO;
import com.matzip.matzipback.like.command.application.service.PostLikeService;
import com.matzip.matzipback.responsemessage.SuccessCode;
import com.matzip.matzipback.responsemessage.SuccessResMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Tag(name = "PostLike", description = "게시글 좋아요")
public class PostLikeController {

    private final PostLikeService postLikeService;

    // 1차 수정완료 - 창윤
    @PostMapping("/post/like")
    @Operation(summary = "게시글 좋아요 등록, 취소", description = "게시글 좋아요를 등록 또는 취소한다.")
    public ResponseEntity<SuccessResMessage> savePostLike(@RequestBody PostLikeReqDTO postLikeReqDTO) {
        boolean resultLike = postLikeService.savePostLike(postLikeReqDTO);

        if (resultLike) {
            // 좋아요 등록
            return ResponseEntity.ok(new SuccessResMessage(SuccessCode.LIKE_SUCCESS));
        }

        // 좋아요 취소
        return ResponseEntity.ok(new SuccessResMessage(SuccessCode.LIKE_DELETE_SUCCESS));
    }
}
