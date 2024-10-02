package com.matzip.matzipback.like.command.application.controller;

import com.matzip.matzipback.like.command.application.dto.ListCmtLikeReqDTO;
import com.matzip.matzipback.like.command.application.service.ListCmtLikeService;
import com.matzip.matzipback.responsemessage.SuccessCode;
import com.matzip.matzipback.responsemessage.SuccessResMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ListCmtLikeController {

    private final ListCmtLikeService listCmtLikeService;

    // 1차 수정 - 창윤
    @PostMapping("/listCmt/like")
    public ResponseEntity<SuccessResMessage> saveListCmtLike(@Valid @RequestBody ListCmtLikeReqDTO listCmtLikeRequest){
        boolean resultLike = listCmtLikeService.saveAndDeleteListCmtLike(listCmtLikeRequest);

        // 좋아요 등록
        if (resultLike) {
            return ResponseEntity.ok(new SuccessResMessage(SuccessCode.LIKE_SUCCESS));
        }

        // 좋아요 취소
        return ResponseEntity.ok(new SuccessResMessage(SuccessCode.LIKE_DELETE_SUCCESS));
    }

}
