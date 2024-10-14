package com.matzip.matzipback.board.command.application.controller;

import com.matzip.matzipback.board.command.application.dto.ReqPostCmtCreateDTO;
import com.matzip.matzipback.board.command.application.dto.ReqPostCmtUpdateDTO;
import com.matzip.matzipback.board.command.application.service.PostCommentService;
import com.matzip.matzipback.common.util.CustomUserUtils;
import com.matzip.matzipback.exception.RestApiException;
import com.matzip.matzipback.responsemessage.SuccessCode;
import com.matzip.matzipback.responsemessage.SuccessResMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.matzip.matzipback.exception.ErrorCode.FORBIDDEN_ACCESS;
import static com.matzip.matzipback.exception.ErrorCode.UNAUTHORIZED_REQUEST;
import static com.matzip.matzipback.responsemessage.SuccessCode.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/back/api/v1")
@Tag(name = "Post Comment", description = "게시글 댓글")
public class PostCmtCommandController {

    private final PostCommentService postCommentService;

    // 댓글 등록
    @PostMapping("/postcomment")
    @Operation(summary = "게시글 댓글 등록", description = "게시글에 댓글을 등록한다.")
    public ResponseEntity<SuccessResMessage> createPostComment(@Valid @RequestBody ReqPostCmtCreateDTO reqPostCmtCreateDTO) {

        try{
            if (CustomUserUtils.getCurrentUserAuthorities().iterator().next().getAuthority().equals("user")) {

                postCommentService.createPostComment(reqPostCmtCreateDTO);
                return ResponseEntity.ok(new SuccessResMessage(BASIC_SAVE_SUCCESS));
            } else {
                throw new RestApiException(FORBIDDEN_ACCESS);
            }
        } catch (NullPointerException e) { throw new RestApiException(UNAUTHORIZED_REQUEST); }
    }

    // 댓글 수정
    @PutMapping("/postcomment")
    @Operation(summary = "게시글 댓글 수정", description = "게시글에 댓글을 수정한다.")
    public ResponseEntity<SuccessResMessage> updatePostComment(
            @Valid @RequestBody ReqPostCmtUpdateDTO reqPostCmtUpdateDTO
    ) {

        try{
            if (CustomUserUtils.getCurrentUserAuthorities().iterator().next().getAuthority().equals("user")) {

                postCommentService.updatePostComment(reqPostCmtUpdateDTO);
                return ResponseEntity.ok(new SuccessResMessage(BASIC_UPDATE_SUCCESS));
            } else {
                throw new RestApiException(FORBIDDEN_ACCESS);
            }
        } catch (NullPointerException e) { throw new RestApiException(UNAUTHORIZED_REQUEST); }
    }

    // 댓글 삭제
    @DeleteMapping("/postcomment/{postCommentSeq}")
    @Operation(summary = "게시글 댓글 삭제", description = "게시글에 댓글을 삭제한다.")
    public ResponseEntity<SuccessResMessage> deletePostComment(@PathVariable Long postCommentSeq) {

        try{
            if (CustomUserUtils.getCurrentUserAuthorities().iterator().next().getAuthority().equals("user")) {

                postCommentService.deletePostComment(postCommentSeq);
                return ResponseEntity.ok(new SuccessResMessage(BASIC_DELETE_SUCCESS));
            } else {
                throw new RestApiException(FORBIDDEN_ACCESS);
            }
        } catch (NullPointerException e) { throw new RestApiException(UNAUTHORIZED_REQUEST); }
    }

}
