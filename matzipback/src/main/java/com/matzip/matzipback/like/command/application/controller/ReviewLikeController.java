package com.matzip.matzipback.like.command.application.controller;

import com.matzip.matzipback.common.util.CustomUserUtils;
import com.matzip.matzipback.exception.ErrorCode;
import com.matzip.matzipback.exception.RestApiException;
import com.matzip.matzipback.like.command.application.service.ReviewLikeService;
import com.matzip.matzipback.responsemessage.SuccessCode;
import com.matzip.matzipback.responsemessage.SuccessResMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.matzip.matzipback.exception.ErrorCode.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("/back/api/v1/review")
@Tag(name = "Like", description = "좋아요")
public class ReviewLikeController {

    private final ReviewLikeService reviewLikeService;

    @PostMapping("/{reviewSeq}/like")
    @Operation(summary = "리뷰 좋아요", description = "리뷰에 좋아요 등록 또는 취소한다.")
    public ResponseEntity<SuccessResMessage> saveReviewLike(
            @PathVariable Long reviewSeq) {

        Long CurrentUserSeq = CustomUserUtils.getCurrentUserSeq();

        if (CurrentUserSeq == null) {
            throw new RestApiException(ErrorCode.UNAUTHORIZED_REQUEST);
        } else {
            try {
                boolean savedReviewLike = reviewLikeService.saveReviewLike(CurrentUserSeq, reviewSeq);

                return ResponseEntity.ok(new SuccessResMessage(
                        savedReviewLike ? SuccessCode.LIKE_SUCCESS : SuccessCode.LIKE_DELETE_SUCCESS));
            } catch (DataIntegrityViolationException e) { throw new RestApiException(NOT_FOUND);}
        }
    }
}
