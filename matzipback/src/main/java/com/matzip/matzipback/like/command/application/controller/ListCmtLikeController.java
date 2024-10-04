package com.matzip.matzipback.like.command.application.controller;

import com.matzip.matzipback.common.util.CustomUserUtils;
import com.matzip.matzipback.exception.RestApiException;
import com.matzip.matzipback.like.command.application.dto.ListCmtLikeReqDTO;
import com.matzip.matzipback.like.command.application.service.ListCmtLikeService;
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

import static com.matzip.matzipback.exception.ErrorCode.FORBIDDEN_ACCESS;
import static com.matzip.matzipback.exception.ErrorCode.UNAUTHORIZED_REQUEST;

@RestController
@RequiredArgsConstructor
@RequestMapping("/back/api/vi")
@Tag(name = "Like", description = "좋아요")
public class ListCmtLikeController {

    private final ListCmtLikeService listCmtLikeService;

    // 1차 수정 - 창윤
    @Operation(summary = "리스트 댓글 좋아요", description = "리스트의 댓글에 좋아요 등록 또는 취소한다.")
    @PostMapping("/listCmt/like")
    public ResponseEntity<SuccessResMessage> saveListCmtLike(@Valid @RequestBody ListCmtLikeReqDTO listCmtLikeRequest){

        try { if (CustomUserUtils.getCurrentUserAuthorities().iterator().next().getAuthority().equals("user")) {

            boolean resultLike = listCmtLikeService.saveAndDeleteListCmtLike(listCmtLikeRequest);
            // 좋아요 등록
            if (resultLike) {
                return ResponseEntity.ok(new SuccessResMessage(SuccessCode.LIKE_SUCCESS));
            }

            // 좋아요 취소
            return ResponseEntity.ok(new SuccessResMessage(SuccessCode.LIKE_DELETE_SUCCESS));
        } else { throw new RestApiException(FORBIDDEN_ACCESS); }

        } catch (NullPointerException e) { throw new RestApiException(UNAUTHORIZED_REQUEST); }
    }

}
