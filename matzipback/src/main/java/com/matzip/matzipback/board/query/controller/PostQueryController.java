package com.matzip.matzipback.board.query.controller;

import com.matzip.matzipback.board.query.dto.PostDetailResponse;
import com.matzip.matzipback.board.query.dto.PostListResponse;
import com.matzip.matzipback.board.query.service.PostQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PostQueryController {

    private final PostQueryService postQueryService;

    /* 1. 검색조건(게시글 제목, 게시글 작성자 닉네임)에 맞는 게시글 목록 조회 */
    @GetMapping("/posts/search")
    public ResponseEntity<PostListResponse> searchPosts(
            // paging 처리
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String postTitle,      // 검색조건1: 게시글 제목
            @RequestParam(required = false) String userNickname    // 검색조건2: 게시글 작성자 닉네임
    ) {
        PostListResponse response = postQueryService.searchPosts(page, size, postTitle, userNickname);

        return ResponseEntity.ok(response);
    }

    /* 2. 게시판 카테고리 별 게시글 목록 조회 */
    @GetMapping("/category/{boardCategorySeq}/posts")
    public ResponseEntity<PostListResponse> getPostsByCategory(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @PathVariable Long boardCategorySeq
    ) {
        PostListResponse response = postQueryService.getPostsByCategory(page, size, boardCategorySeq);

        return ResponseEntity.ok(response);
    }

    /* 3. 게시글 상세 조회 */
    @GetMapping("/post/{postSeq}")
    public ResponseEntity<PostDetailResponse> getPostDetail(@PathVariable Long postSeq) {

        PostDetailResponse response = postQueryService.getPostDetail(postSeq);

        return ResponseEntity.ok(response);
    }
}
