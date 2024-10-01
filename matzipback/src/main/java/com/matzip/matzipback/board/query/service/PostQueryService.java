package com.matzip.matzipback.board.query.service;

import com.matzip.matzipback.board.command.application.service.PostCommentService;
import com.matzip.matzipback.board.query.dto.*;
import com.matzip.matzipback.board.query.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostQueryService {

    private final PostMapper postMapper;
    private final PostCommentService postCommentService;

    /* 1. 게시글 검색 (검색조건 : 게시글 이름, 게시글 작성자 닉네임) */
    @Transactional(readOnly = true)
    public PostListResponse searchPosts(Integer page, Integer size, String postTitle, String userNickname) {

        // offset : 몇번째 row부터 출력할지 설정
        int offset = (page -1) * size;

        // 검색 조건에 맞는 게시글 목록 조회
        List<PostDTO> posts = postMapper.searchPosts(offset, size, postTitle, userNickname);

        // 검색 조건에 맞는 게시글의 갯수
        long totalPosts = postMapper.countPostsBySearch(postTitle, userNickname);

        return PostListResponse.builder()
                .posts(posts)
                .currentPage(page)
                .totalPages((int) Math.ceil((double) totalPosts / size))
                .totalPosts(totalPosts)
                .build();
    }

    /* 2. 게시판 카테고리 별 게시글 목록 조회 */
    @Transactional(readOnly = true)
    public PostListResponse getPostsByCategory(Integer page, Integer size, Long boardCategorySeq) {

        // offset : 몇번째 row부터 출력할지 설정
        int offset = (page -1) * size;

        // 해당 게시판에 속해있는 게시글 조회
        List<PostDTO> posts = postMapper.getPostsByCategory(offset, size, boardCategorySeq);

        // 해당 게시판 카테고리에 작성된 게시글 갯수
        long totalPosts = postMapper.countPostsByCategory(boardCategorySeq);

        return PostListResponse.builder()
                .posts(posts)
                .currentPage(page)
                .totalPages((int) Math.ceil((double)totalPosts / size))
                .totalPosts(totalPosts)
                .build();
    }

    /* 3. 게시글 상세 조회 */
    @Transactional(readOnly = true)
    public PostDetailResponse getPostDetail(Long postSeq) {

        // 게시글 기본정보 조회
        PostDTO post = postMapper.getPostDetail(postSeq);

        // 삭제, 정지 등의 이유로 해당 글이 없을 때 이후 상세 조회를 하지 않고 null 반환
        if (post == null) {
            // throw new NotFoundException("해당 게시글을 찾지 못했습니다. 게시물 코드 : " + post);
            return PostDetailResponse.builder()
                    .post(post)
                    .build();
        }

        // 태그 리스트 조회
        List<PostTagDTO> tags = postMapper.getPostTags(postSeq);

        // 관련된 나의 맛집리스트 조회
        ListDTO list = postMapper.getPostList(postSeq);

        // 관련된 음식점 조회
        RestaurantDTO restaurant = postMapper.getPostRestaurant(postSeq);

        // 좋아요 갯수 조회
        Long likeCount = postMapper.getLikeCount(postSeq);

        // 게시글에 달린 댓글 조회
        List<PostCommentDTO> comments = postMapper.getPostComment(postSeq);

        return PostDetailResponse.builder()
                .post(post)
                .tags(tags)
                .list(list)
                .restaurant(restaurant)
                .likeCount(likeCount)
                .comments(comments)
                .build();
    }

    /* 4. 게시판 별 인기 태그 조회 */
    @Transactional(readOnly = true)
    public PopularTagResponse getPopularTag(Long boardSeq) {

        List<String> tags = postMapper.getPopularTag(boardSeq);

        return PopularTagResponse.builder()
                .tags(tags)
                .build();
    }
}
