package com.matzip.matzipback.like.command.application.controller;

import com.matzip.matzipback.like.command.application.dto.PostCmtLikeReqDTO;
import com.matzip.matzipback.like.command.application.service.PostCmtLikeService;
import com.matzip.matzipback.responsemessage.SuccessCode;
import com.matzip.matzipback.responsemessage.SuccessResMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
@Tag(name = "PostCommentLike", description = "게시글 댓글 좋아요")
public class PostCmtLikeController {

    private final PostCmtLikeService postCmtLikeService;

    // 게시글 댓글 좋아요
    @PostMapping("/{postSeq}/postcomment/{postCommentSeq}/like")
    @Operation(summary = "게시글 댓글 좋아요/좋아요 취소", description = "게시글에 댓글에 좋아요를 표시/취소한다.")
    public ResponseEntity<SuccessResMessage> savePostCmtLike(
            @PathVariable Long postSeq,
            @PathVariable Long postCommentSeq) {
         PostCmtLikeReqDTO postCmtLikeReqDTO = new PostCmtLikeReqDTO();
         postCmtLikeReqDTO.setPostSeq(postSeq);
         postCmtLikeReqDTO.setPostCommentSeq(postCommentSeq);

         boolean savedPostCmtLike = postCmtLikeService.savePostCmtLike(postCmtLikeReqDTO);

         if (savedPostCmtLike) {
             return ResponseEntity.ok(new SuccessResMessage(SuccessCode.BASIC_SAVE_SUCCESS));
         } else {
             return ResponseEntity.ok(new SuccessResMessage(SuccessCode.BASIC_DELETE_SUCCESS));
         }

    }

}
