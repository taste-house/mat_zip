package com.matzip.matzipback.board.command.application.controller;

import com.matzip.matzipback.board.command.application.dto.ReqPostCmtCreateDTO;
import com.matzip.matzipback.board.command.application.dto.ReqPostCmtUpdateDTO;
import com.matzip.matzipback.board.command.application.dto.ResPostCmtDTO;
import com.matzip.matzipback.board.command.application.service.PostCommentService;
import com.matzip.matzipback.board.command.domain.aggregate.PostComment;
import com.matzip.matzipback.report.command.dto.PostCmtReportResMessageDTO;
import com.matzip.matzipback.responsemessage.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        ResPostCmtDTO postComment = postCommentService.updatePostComment(reqPostCmtUpdateDTO);

        return ResponseEntity.ok(postComment);
    }

    // 댓글 삭제
    @DeleteMapping("/postcomment/{postCommentSeq}")
    public ResponseEntity<ResPostCmtDTO> deletePostComment(@PathVariable Long postCommentSeq) {
        PostComment postComment = postCommentService.deletePostComment(postCommentSeq);

        if (postComment.getPostCommentStatus().equals("delete")) { // 삭제 성공
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResPostCmtDTO(201, ResponseMessage.SAVE_SUCCESS.getMessage(), postComment.getPostSeq()));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResPostCmtDTO(204, ResponseMessage.SAVE_FAIL.getMessage(), postComment.getPostSeq()));
    }

}
