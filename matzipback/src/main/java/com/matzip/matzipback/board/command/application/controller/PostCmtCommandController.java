package com.matzip.matzipback.board.command.application.controller;

import com.matzip.matzipback.board.command.application.dto.ReqPostCmtCreateDTO;
import com.matzip.matzipback.board.command.application.dto.ReqPostCmtUpdateDTO;
import com.matzip.matzipback.board.command.application.service.PostCommentService;
import com.matzip.matzipback.responsemessage.SuccessResMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.matzip.matzipback.responsemessage.SuccessCode.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Tag(name = "PostComment", description = "게시글 댓글")
public class PostCmtCommandController {

    private final PostCommentService postCommentService;

    // 댓글 등록
    @PostMapping("/postcomment")
    @Operation(summary = "게시글 댓글 등록", description = "게시글에 댓글을 등록한다.")
    public ResponseEntity<SuccessResMessage> createPostComment(@Valid @RequestBody ReqPostCmtCreateDTO reqPostCmtCreateDTO) {
        postCommentService.createPostComment(reqPostCmtCreateDTO);
        return ResponseEntity.ok(new SuccessResMessage(BASIC_SAVE_SUCCESS));
    }

    // 댓글 수정
    @PutMapping("/postcomment")
    @Operation(summary = "게시글 댓글 수정", description = "게시글에 댓글을 수정한다.")
    public ResponseEntity<SuccessResMessage> updatePostComment(
            @Valid @RequestBody ReqPostCmtUpdateDTO reqPostCmtUpdateDTO
    ) {
        postCommentService.updatePostComment(reqPostCmtUpdateDTO);
        return ResponseEntity.ok(new SuccessResMessage(BASIC_UPDATE_SUCCESS));
    }

    // 댓글 삭제
    @DeleteMapping("/postcomment/{postCommentSeq}")
    @Operation(summary = "게시글 댓글 삭제", description = "게시글에 댓글을 삭제한다.")
    public ResponseEntity<SuccessResMessage> deletePostComment(@PathVariable Long postCommentSeq) {
        postCommentService.deletePostComment(postCommentSeq);
        return ResponseEntity.ok(new SuccessResMessage(BASIC_DELETE_SUCCESS));
    }

}
