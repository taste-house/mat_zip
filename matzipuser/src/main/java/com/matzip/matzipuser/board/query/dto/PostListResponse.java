package com.matzip.matzipuser.board.query.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class PostListResponse {
    private List<PostDTO> posts;    // 검색조건에 부합하는 게시글 리스트
    private int currentPage;        // 현재 페이지
    private int totalPages;         // 전체 페이지 수
    private long totalPosts;        // 검색조건에 부합하는 총 게시글 수

}
