package com.matzip.matzipback.like.command.application.controller;


import com.matzip.matzipback.like.command.application.dto.ListLikeReqDTO;
import com.matzip.matzipback.like.command.application.service.ListLikeService;
import com.matzip.matzipback.responsemessage.SuccessCode;
import com.matzip.matzipback.responsemessage.SuccessResMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "ListLike", description = "리스트 좋아요")
public class ListLikeController {

    private final ListLikeService listLikeService;

    // 1차 수정 완료 -- 창윤
    @PostMapping("/list/like")
    @Operation(summary = "리스트 좋아요 등록, 취소", description = "리스트 좋아요를 등록 또는 취소한다.")
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
