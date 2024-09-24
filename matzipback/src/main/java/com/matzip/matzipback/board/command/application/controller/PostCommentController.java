package com.matzip.matzipback.board.command.application.controller;

import com.matzip.matzipback.board.command.application.dto.ReqPostCmtCreateDTO;
import com.matzip.matzipback.board.command.application.dto.ReqPostCmtUpdateDTO;
import com.matzip.matzipback.board.command.application.dto.ResPostCmtCreateDTO;
import com.matzip.matzipback.board.command.application.dto.ResPostCmtUpdateDTO;
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
    @PostMapping("/postComment")
    public ResponseEntity<ResPostCmtCreateDTO> createPostComment(@RequestBody ReqPostCmtCreateDTO reqPostCmtCreateDTO) {
        ResPostCmtCreateDTO postComment = postCommentService.createPostComment(reqPostCmtCreateDTO);

        return ResponseEntity.ok(postComment);
    }

    // 댓글 수정
    @PutMapping("/postComment")
    public ResponseEntity<ResPostCmtUpdateDTO> updatePostComment(@RequestBody ReqPostCmtUpdateDTO reqPostCmtUpdateDTO) {
        ResPostCmtUpdateDTO postComment = postCommentService.updatePostComment(reqPostCmtUpdateDTO);

        return ResponseEntity.ok(postComment);
    }
}
