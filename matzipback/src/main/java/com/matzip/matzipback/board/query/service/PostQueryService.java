package com.matzip.matzipback.board.query.service;

import com.matzip.matzipback.board.query.dto.PostDto;
import com.matzip.matzipback.board.query.dto.PostListResponse;
import com.matzip.matzipback.board.query.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostQueryService {

    private final PostMapper postMapper;

    /* 1. 게시글 검색 (검색조건 : 게시글 이름, 게시글 작성자 닉네임) */
    @Transactional(readOnly = true)
    public PostListResponse searchPosts(Integer page, Integer size, String postTitle, String userNickname) {

        // offset : 몇번째 row부터 출력할지 설정
        int offset = (page -1) * size;

        // 검색 조건에 맞는 게시글 목록 조회
        List<PostDto> posts = postMapper.searchPosts(offset, size, postTitle, userNickname);

        // 검색 조건에 맞는 게시글의 갯수
        long totalPosts = postMapper.countPosts(postTitle, userNickname);

        return PostListResponse.builder()
                .posts(posts)
                .currentPage(page)
                .totalPages((int) Math.ceil((double) totalPosts / size))
                .totalPosts(totalPosts)
                .build();
    }
}
