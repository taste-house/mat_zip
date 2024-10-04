package com.matzip.matzipback.board.query.controller;

import com.matzip.matzipback.board.query.dto.PostCmtListResponseDTO;
import com.matzip.matzipback.board.query.service.PostCmtQueryService;
import com.matzip.matzipback.common.util.CustomUserUtils;
import com.matzip.matzipback.exception.RestApiException;
import com.matzip.matzipback.responsemessage.SuccessCode;
import com.matzip.matzipback.responsemessage.SuccessSearchResMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.matzip.matzipback.exception.ErrorCode.FORBIDDEN_ACCESS;
import static com.matzip.matzipback.exception.ErrorCode.UNAUTHORIZED_REQUEST;

@RestController
@RequiredArgsConstructor
@RequestMapping("/back/api/vi")
@Tag(name = "Post Comment", description = "게시글 댓글")
public class PostCmtQueryController {

    private final PostCmtQueryService postCmtQueryService;

    @GetMapping("/users/{userSeq}/comments")
    @Operation(summary = "게시글 댓글 목록 조회", description = "회원이 작성한 게시글 댓글 목록을 조회한다.")
    public ResponseEntity<SuccessSearchResMessage<?>> getCommentsByUserSeq(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam Long userSeq
    ) {

            return ResponseEntity.ok()
                    .body(new SuccessSearchResMessage<>(
                            SuccessCode.BASIC_GET_SUCCESS,
                            postCmtQueryService.getCommentsByUserSeq(page, size, userSeq)));
    }
}
