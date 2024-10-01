package com.matzip.matzipback.board.command.application.controller;

import com.matzip.matzipback.board.command.application.dto.ReqPostCmtCreateDTO;
import com.matzip.matzipback.board.command.application.dto.ReqPostCmtUpdateDTO;
import com.matzip.matzipback.board.command.application.dto.ResPostCmtDTO;
import com.matzip.matzipback.board.command.application.service.PostCommentService;
import com.matzip.matzipback.board.command.domain.aggregate.PostComment;
import com.matzip.matzipback.responsemessage.ResponseMessage;
import com.matzip.matzipback.responsemessage.SuccessResMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.matzip.matzipback.responsemessage.SuccessCode.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PostCmtCommandController {

    private final PostCommentService postCommentService;

    // 댓글 등록
    @PostMapping("/postcomment")
    public ResponseEntity<SuccessResMessage> createPostComment(@Valid @RequestBody ReqPostCmtCreateDTO reqPostCmtCreateDTO) {
        postCommentService.createPostComment(reqPostCmtCreateDTO);
        return ResponseEntity.ok(new SuccessResMessage(BASIC_SAVE_SUCCESS));
    }

    // 댓글 수정
    @PutMapping("/postcomment")
    public ResponseEntity<SuccessResMessage> updatePostComment(
            @Valid @RequestBody ReqPostCmtUpdateDTO reqPostCmtUpdateDTO
    ) {
        postCommentService.updatePostComment(reqPostCmtUpdateDTO);
        return ResponseEntity.ok(new SuccessResMessage(BASIC_UPDATE_SUCCESS));
    }

    // 댓글 삭제
    @DeleteMapping("/postcomment/{postCommentSeq}")
    public ResponseEntity<SuccessResMessage> deletePostComment(@PathVariable Long postCommentSeq) {
        postCommentService.deletePostComment(postCommentSeq);
        return ResponseEntity.ok(new SuccessResMessage(BASIC_DELETE_SUCCESS));
    }

}
