package com.matzip.matzipback.like.command.application.controller;

import com.matzip.matzipback.like.command.application.dto.PostCmtLikeReqDTO;
import com.matzip.matzipback.like.command.application.dto.PostCmtLikeResMessageDTO;
import com.matzip.matzipback.like.command.application.service.PostCmtLikeService;
import com.matzip.matzipback.like.command.domain.aggregate.Like;
import com.matzip.matzipback.responsemessage.ResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
@Tag(name = "PostCmtLike", description = "게시글 댓글 좋아요")
public class PostCmtLikeController {

    private final PostCmtLikeService postCmtLikeService;

    // 게시글 댓글 좋아요
    @PostMapping("/{postSeq}/postcomment/{postCommentSeq}/like")
    @Operation(summary = "게시글 댓글 좋아요 등록, 취소", description = "게시글 댓글 좋아요를 등록 또는 취소한다.")
    public ResponseEntity<PostCmtLikeResMessageDTO> savePostCmtLike(@RequestBody PostCmtLikeReqDTO postCmtLikeReqDTO) {
         Like resultLike = postCmtLikeService.savePostCmtLike(postCmtLikeReqDTO);

        if (resultLike != null) {
            // 좋아요 등록
            return ResponseEntity.status(HttpStatus.CREATED).body(new PostCmtLikeResMessageDTO(201, ResponseMessage.SAVE_SUCCESS.getMessage(), resultLike.getPostSeq()));
        }

        // 좋아요 취소
        return ResponseEntity.status(HttpStatus.OK).body(new PostCmtLikeResMessageDTO(200, ResponseMessage.SAVE_FAIL.getMessage(), postCmtLikeReqDTO.getPostSeq()));
    }

}
