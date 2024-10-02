package com.matzip.matzipback.like.command.application.controller;


import com.matzip.matzipback.like.command.application.dto.ListLikeReqDTO;
import com.matzip.matzipback.like.command.application.service.ListLikeService;
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
public class ListLikeController {

    private final ListLikeService listLikeService;

    // 1차 수정중 -- 창윤
    @PostMapping("/list/like")
    public ResponseEntity<SuccessResMessage> saveListLike(@Valid @RequestBody ListLikeReqDTO listLikeRequest){
        boolean resultLike = listLikeService.saveAndDeleteListLike(listLikeRequest);

        // 리스트 좋아요 등록
        if (resultLike) {
            return ResponseEntity.ok(new SuccessResMessage(SuccessCode.LIKE_SUCCESS));
        }

        // 리스트 좋아요 취소
        return ResponseEntity.ok(new SuccessResMessage(SuccessCode.LIKE_DELETE_SUCCESS));
    }


}
