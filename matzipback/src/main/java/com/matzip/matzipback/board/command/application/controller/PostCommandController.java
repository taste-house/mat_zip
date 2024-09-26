package com.matzip.matzipback.board.command.application.controller;

import com.matzip.matzipback.board.command.application.service.PostCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PostCommandController {

    private final PostCommandService postCommandService;

    /* 1. 게시글 등록 */

    /* 2. 게시글 수정 */

    /* 3. 게시글 삭제 */
    @DeleteMapping("/post/{postSeq}")
    public ResponseEntity<Long> deletePost(@PathVariable Long postSeq) {

        Long boardCategorySeq = postCommandService.deletePost(postSeq);

        return ResponseEntity.ok(boardCategorySeq);
    }

}
