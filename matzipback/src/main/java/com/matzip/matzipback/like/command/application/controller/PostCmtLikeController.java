package com.matzip.matzipback.like.command.application.controller;

import com.matzip.matzipback.common.util.CustomUserUtils;
import com.matzip.matzipback.exception.RestApiException;
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

import static com.matzip.matzipback.exception.ErrorCode.FORBIDDEN_ACCESS;
import static com.matzip.matzipback.exception.ErrorCode.UNAUTHORIZED_REQUEST;

@RestController
@RequiredArgsConstructor
@RequestMapping("/back/api/v1/post")
@Tag(name = "Like", description = "좋아요")
public class PostCmtLikeController {

    private final PostCmtLikeService postCmtLikeService;

    // 게시글 댓글 좋아요
    @PostMapping("/{postSeq}/postcomment/{postCommentSeq}/like")
    @Operation(summary = "게시글 댓글 좋아요", description = "게시글의 댓글에 좋아요 등록 또는 취소한다.")
    public ResponseEntity<SuccessResMessage> savePostCmtLike(
            @PathVariable Long postCommentSeq) {

        try { if (CustomUserUtils.getCurrentUserAuthorities().iterator().next().getAuthority().equals("user")) {

            PostCmtLikeReqDTO postCmtLikeReqDTO = new PostCmtLikeReqDTO();
            postCmtLikeReqDTO.setPostCommentSeq(postCommentSeq);

            boolean savedPostCmtLike = postCmtLikeService.savePostCmtLike(postCmtLikeReqDTO);

            if (savedPostCmtLike) {
                return ResponseEntity.ok(new SuccessResMessage(SuccessCode.BASIC_SAVE_SUCCESS));
            } else {
                return ResponseEntity.ok(new SuccessResMessage(SuccessCode.BASIC_DELETE_SUCCESS));
            }
        } else { throw new RestApiException(FORBIDDEN_ACCESS); }

        } catch (NullPointerException e) { throw new RestApiException(UNAUTHORIZED_REQUEST); }
    }

}
