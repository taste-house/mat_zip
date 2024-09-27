package com.matzip.matzipback.like.query.controller;

import com.matzip.matzipback.like.query.dto.LikedPostDTO;
import com.matzip.matzipback.like.query.dto.LikedPostResMessageDTO;
import com.matzip.matzipback.like.query.service.LikeQueryService;
import com.matzip.matzipback.responsemessage.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class LikeController {

    private final LikeQueryService likeQueryService;

    // 좋아요한 게시글 조회
    @GetMapping("/{userSeq}/liked-posts")
    public ResponseEntity<LikedPostResMessageDTO> likedPosts(@PathVariable("userSeq") Long userSeq) {
        List<LikedPostDTO> myLikedPost = likeQueryService.findMyLikedPost(userSeq);

        return ResponseEntity.status(HttpStatus.OK).body(new LikedPostResMessageDTO(200, ResponseMessage.FOUND.getMessage(), myLikedPost));
    }
}
