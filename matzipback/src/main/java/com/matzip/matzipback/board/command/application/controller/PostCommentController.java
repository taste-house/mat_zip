package com.matzip.matzipback.board.command.application.controller;

import com.matzip.matzipback.board.command.application.dto.ReqPostCmtCreateDTO;
import com.matzip.matzipback.board.command.application.dto.ReqPostCmtDeleteDTO;
import com.matzip.matzipback.board.command.application.dto.ReqPostCmtUpdateDTO;
import com.matzip.matzipback.board.command.application.dto.ResPostCmtDTO;
import com.matzip.matzipback.board.command.application.service.PostCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PostCommentController {

    private final PostCommentService postCommentService;

    // 댓글 등록
    @PostMapping("/postcomment")
    public ResponseEntity<ResPostCmtDTO> createPostComment(@RequestBody ReqPostCmtCreateDTO reqPostCmtCreateDTO) {
        ResPostCmtDTO postComment = postCommentService.createPostComment(reqPostCmtCreateDTO);

        return ResponseEntity.ok(postComment);
    }

    // 댓글 수정
    @PutMapping("/postcomment")
    public ResponseEntity<ResPostCmtDTO> updatePostComment(@RequestBody ReqPostCmtUpdateDTO reqPostCmtUpdateDTO) {
        ResPostCmtDTO postComment = postCommentService.updatePostComment(reqPostCmtUpdateDTO);

        return ResponseEntity.ok(postComment);
    }

    // 댓글 삭제
    @DeleteMapping("/postcomment/{postCommentSeq}")
    public ResponseEntity<ResPostCmtDTO> deletePostComment(@RequestBody ReqPostCmtDeleteDTO reqPostCmtDeleteDTO) {
        ResPostCmtDTO postComment = postCommentService.deletePostComment(reqPostCmtDeleteDTO);

        return ResponseEntity.ok(postComment);
    }

}
