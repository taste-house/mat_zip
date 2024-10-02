package com.matzip.matzipback.board.query.controller;

import com.matzip.matzipback.board.query.dto.PopularTagResponse;
import com.matzip.matzipback.board.query.dto.PostDetailResponse;
import com.matzip.matzipback.board.query.dto.PostListResponse;
import com.matzip.matzipback.board.query.service.PostQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Tag(name = "Post", description = "게시글")
public class PostQueryController {

    private final PostQueryService postQueryService;

    /* 1. 검색조건(게시글 제목, 게시글 작성자 닉네임)에 맞는 게시글 목록 조회 */
    @GetMapping("/posts/search")
    @Operation(summary = "게시글 검색", description = "게시글 제목, 작성자 닉네임으로 게시글을 검색한다.")
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
    @Operation(summary = "게시판 내 게시글 목록 조회", description = "게시판 카테고리 별로 게시글 목록을 조회한다.")
    public ResponseEntity<PostListResponse> getPostsByCategory(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @PathVariable Long boardCategorySeq
    ) {
        PostListResponse response = postQueryService.getPostsByCategory(page, size, boardCategorySeq);

        return ResponseEntity.ok(response);
    }

    /* 3. 게시글 상세 조회 */
    @GetMapping("/posts/{postSeq}")
    @Operation(summary = "게시글 상세 조회", description = "게시글을 상세 조회한다.")
    public ResponseEntity<PostDetailResponse> getPostDetail(@PathVariable Long postSeq) {

        PostDetailResponse response = postQueryService.getPostDetail(postSeq);

        return ResponseEntity.ok(response);
    }

    /* 4. 게시판 별 인기 태그 조회 */
    @GetMapping("/boards/{boardSeq}/popular-tag")
    @Operation(summary = "게시판 인기 태그 조회", description = "게시판 카테고리 별로 인기 태그를 조회한다.")
    public ResponseEntity<PopularTagResponse> getPopularTag(@PathVariable Long boardSeq) {

        PopularTagResponse response = postQueryService.getPopularTag(boardSeq);

        return ResponseEntity.ok(response);
    }
}
