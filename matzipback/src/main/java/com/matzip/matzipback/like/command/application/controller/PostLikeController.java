package com.matzip.matzipback.like.command.application.controller;

import com.matzip.matzipback.like.command.application.dto.PostLikeReqDTO;
import com.matzip.matzipback.like.command.application.dto.PostLikeResMessageDTO;
import com.matzip.matzipback.like.command.application.service.PostLikeService;
import com.matzip.matzipback.like.command.domain.aggregate.Like;
import com.matzip.matzipback.responsemessage.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PostLikeController {

    private final PostLikeService postLikeService;


    @PostMapping("/post/like")
    public ResponseEntity<PostLikeResMessageDTO> savePostLike(@RequestBody PostLikeReqDTO postLikeReqDTO) {
        Like resultLike = postLikeService.savePostLike(postLikeReqDTO);

        if (resultLike != null) {
            // 좋아요 등록
            return ResponseEntity.status(HttpStatus.CREATED).body(new PostLikeResMessageDTO(201, ResponseMessage.SAVE_SUCCESS.getMessage(), resultLike.getPostSeq()));
        }

        // 좋아요 취소
        return ResponseEntity.status(HttpStatus.OK).body(new PostLikeResMessageDTO(200, ResponseMessage.SAVE_FAIL.getMessage(), -1));
    }
}
