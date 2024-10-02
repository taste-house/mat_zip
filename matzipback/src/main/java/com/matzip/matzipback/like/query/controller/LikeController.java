package com.matzip.matzipback.like.query.controller;

import com.matzip.matzipback.like.query.dto.LikedPostDTO;
import com.matzip.matzipback.like.query.service.LikeQueryService;
import com.matzip.matzipback.responsemessage.SuccessCode;
import com.matzip.matzipback.responsemessage.SuccessSearchResMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Tag(name = "Like", description = "좋아요")
public class LikeController {

    private final LikeQueryService likeQueryService;

    // 1차 수정 완료 - 창윤
    // 좋아요한 게시글 조회
    @GetMapping("/liked-posts/{userSeq}")
    @Operation(summary = "좋아요한 게시글 조회", description = "좋아요한 게시글을 조회한다.")
    public ResponseEntity<SuccessSearchResMessage<?>> likedPosts(@PathVariable(required = false) Long userSeq,
                                                                 @RequestParam(value = "page", defaultValue = "1") long page) {
        List<LikedPostDTO> myLikedPost = likeQueryService.findMyLikedPost(userSeq, page);

        return ResponseEntity.ok(new SuccessSearchResMessage<>(SuccessCode.BASIC_GET_SUCCESS, myLikedPost));
    }
}
