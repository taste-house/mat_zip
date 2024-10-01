package com.matzip.matzipback.board.command.application.controller;

import com.matzip.matzipback.board.command.application.dto.ReqPostCmtCreateDTO;
import com.matzip.matzipback.board.command.application.dto.ReqPostCmtUpdateDTO;
import com.matzip.matzipback.board.command.application.dto.ResPostCmtDTO;
import com.matzip.matzipback.board.command.application.service.PostCommentService;
import com.matzip.matzipback.board.command.domain.aggregate.PostComment;
import com.matzip.matzipback.responsemessage.ResponseMessage;
import com.matzip.matzipback.responsemessage.SuccessResMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import static com.matzip.matzipback.responsemessage.SuccessCode.BASIC_DELETE_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PostCmtCommandController {

    private final PostCommentService postCommentService;

    // 댓글 등록
    @PostMapping("/postcomment")
    public ResponseEntity<ResPostCmtDTO> createPostComment(@RequestBody ReqPostCmtCreateDTO reqPostCmtCreateDTO) {
        PostComment postComment = postCommentService.createPostComment(reqPostCmtCreateDTO);

        if (postComment.getPostCommentSeq() != null) {  // 등록 성공
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResPostCmtDTO(201, ResponseMessage.SAVE_SUCCESS.getMessage(), postComment.getPostSeq()));
        }

        // 등록 실패
        return ResponseEntity.status(HttpStatus.OK).body(new ResPostCmtDTO(204, ResponseMessage.SAVE_FAIL.getMessage(), postComment.getPostSeq()));
    }

    // 댓글 수정
    @PutMapping("/postcomment")
    public ResponseEntity<ResPostCmtDTO> updatePostComment(@RequestBody ReqPostCmtUpdateDTO reqPostCmtUpdateDTO) {
        PostComment postComment = postCommentService.updatePostComment(reqPostCmtUpdateDTO);

        if (postComment != null) {  // 수정 성공
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResPostCmtDTO(201, ResponseMessage.SAVE_SUCCESS.getMessage(), postComment.getPostSeq()));
        }

        // 수정 실패
        return ResponseEntity.status(HttpStatus.OK).body(new ResPostCmtDTO(204, ResponseMessage.SAVE_FAIL.getMessage(), reqPostCmtUpdateDTO.getPostSeq()));
    }

    // 댓글 삭제
    @DeleteMapping("/postcomment/{postCommentSeq}")
    public ResponseEntity<SuccessResMessage> deletePostComment(@PathVariable Long postCommentSeq) {
        postCommentService.deletePostComment(postCommentSeq);
        return ResponseEntity.ok(new SuccessResMessage(BASIC_DELETE_SUCCESS));
    }

}
