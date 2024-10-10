package com.matzip.matzipback.board.query.controller;

import com.matzip.matzipback.board.query.dto.BoardCategoryDTO;
import com.matzip.matzipback.board.query.service.BoardQueryService;
import com.matzip.matzipback.common.util.CustomUserUtils;
import com.matzip.matzipback.exception.RestApiException;
import com.matzip.matzipback.responsemessage.SuccessCode;
import com.matzip.matzipback.responsemessage.SuccessResMessage;
import com.matzip.matzipback.responsemessage.SuccessSearchResMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.matzip.matzipback.exception.ErrorCode.FORBIDDEN_ACCESS;
import static com.matzip.matzipback.exception.ErrorCode.UNAUTHORIZED_REQUEST;

@RestController
@RequiredArgsConstructor
@RequestMapping("/back/api/v1")
@Tag(name = "Board", description = "게시판")
public class BoardQueryController {

    private final BoardQueryService boardQueryService;

    @GetMapping("/board")
    @Operation(summary = "게시판 카테고리 조회", description = "게시판 카테고리를 조회한다.")
    public ResponseEntity<SuccessSearchResMessage<?>> getBoardCategories() {
        return ResponseEntity.ok()
                .body(new SuccessSearchResMessage<>(
                        SuccessCode.BASIC_GET_SUCCESS,
                        boardQueryService.getBoardCategories()));
    }

    @GetMapping("/{userSeq}/board/like")
    @Operation(summary = "즐겨찾기 한 게시판 목록 조회", description = "즐겨찾기 한 게시판 목록을 조회한다.")
    public ResponseEntity<SuccessSearchResMessage<?>> getBoardFavorCategories(@PathVariable("userSeq") String userSeq) {

        try { if (CustomUserUtils.getCurrentUserAuthorities().iterator().next().getAuthority().equals("user")) {

            return ResponseEntity.ok()
                    .body(new SuccessSearchResMessage<>(
                            SuccessCode.BASIC_GET_SUCCESS
                            , boardQueryService.getBoardFavorCategories(userSeq)));
        } else { throw new RestApiException(FORBIDDEN_ACCESS); }

        } catch (NullPointerException e) { throw new RestApiException(UNAUTHORIZED_REQUEST); }
    }

}
