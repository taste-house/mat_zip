package com.matzip.matzipback.like.command.application.controller;

import com.matzip.matzipback.like.command.application.dto.PostCmtLikeReqDTO;
import com.matzip.matzipback.like.command.application.service.PostCmtLikeService;
import com.matzip.matzipback.responsemessage.SuccessCode;
import com.matzip.matzipback.responsemessage.SuccessResMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
@Tag(name = "Post", description = "게시글")
public class PostCmtLikeController {

    private final PostCmtLikeService postCmtLikeService;

    // 2차 수정 - 창윤
    // 게시글 댓글 좋아요
    @PostMapping("/postcomment/like")
    @Operation(summary = "게시글 댓글 좋아요 등록, 취소", description = "게시글 댓글 좋아요를 등록 또는 취소")
    public ResponseEntity<SuccessResMessage> savePostCmtLike(
            @Valid @RequestBody PostCmtLikeReqDTO postCmtLikeReqDTO) {

         boolean savedPostCmtLike = postCmtLikeService.savePostCmtLike(postCmtLikeReqDTO);

         if (savedPostCmtLike) {
             return ResponseEntity.ok(new SuccessResMessage(SuccessCode.BASIC_SAVE_SUCCESS));
         } else {
             return ResponseEntity.ok(new SuccessResMessage(SuccessCode.BASIC_DELETE_SUCCESS));
         }

    }

}
