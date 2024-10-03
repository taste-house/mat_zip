package com.matzip.matzipback.like.command.application.controller;

import com.matzip.matzipback.like.command.application.dto.PostCmtLikeReqDTO;
import com.matzip.matzipback.like.command.application.service.PostCmtLikeService;
import com.matzip.matzipback.responsemessage.SuccessCode;
import com.matzip.matzipback.responsemessage.SuccessResMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostCmtLikeController {

    private final PostCmtLikeService postCmtLikeService;

    // 게시글 댓글 좋아요
    @PostMapping("/{postSeq}/postcomment/{postCommentSeq}/like")
    public ResponseEntity<SuccessResMessage> savePostCmtLike(
            @PathVariable Long postCommentSeq) {
         PostCmtLikeReqDTO postCmtLikeReqDTO = new PostCmtLikeReqDTO();
         postCmtLikeReqDTO.setPostCommentSeq(postCommentSeq);

         boolean savedPostCmtLike = postCmtLikeService.savePostCmtLike(postCmtLikeReqDTO);

         if (savedPostCmtLike) {
             return ResponseEntity.ok(new SuccessResMessage(SuccessCode.BASIC_SAVE_SUCCESS));
         } else {
             return ResponseEntity.ok(new SuccessResMessage(SuccessCode.BASIC_DELETE_SUCCESS));
         }

    }

}
