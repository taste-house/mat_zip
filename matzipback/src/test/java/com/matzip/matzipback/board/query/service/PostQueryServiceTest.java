package com.matzip.matzipback.board.query.service;

import com.matzip.matzipback.board.query.dto.PopularTagResponse;
import com.matzip.matzipback.board.query.dto.PostDetailResponse;
import com.matzip.matzipback.board.query.dto.PostListResponse;
import com.matzip.matzipback.exception.RestApiException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostQueryServiceTest {

    @Autowired
    private PostQueryService postQueryService;

    @DisplayName("게시글 제목, 작성자 닉네임으로 게시글 목록 검색")
    @ParameterizedTest
    @CsvSource({"맛집, nick"})
    void searchPosts(String postTitle, String userNickname) {

        // when
        PostListResponse foundPosts = postQueryService.searchPosts(1, 10, postTitle, userNickname);

        // then
        assertFalse(foundPosts.getPosts().isEmpty()); // 검색된 게시글이 0건이 아님을 확인
        System.out.println("검색된 게시글: " +foundPosts);         // 검색된 게시글 목록 출력
    }

    @DisplayName("게시판 카테고리 별 게시글 목록 조회")
    @ParameterizedTest
    @ValueSource(longs = {1L})
    void getPostsByCategory(Long boardSeq) {

        // when
        PostListResponse foundPosts = postQueryService.getPostsByCategory(1, 10, boardSeq);

        // then
        assertFalse(foundPosts.getPosts().isEmpty()); // 카테고리 1 게시판 내 게시글이 0건이 아님을 확인
        System.out.println("검색된 게시글: " +foundPosts);         // 카테고리 1 게시판 내 게시글 목록 출력
    }

    @DisplayName("게시글 상세 조회")
    @ParameterizedTest
    @ValueSource(longs = {1L})
    void getPostDetail(Long postSeq) {

        // when
        PostDetailResponse postDetail = postQueryService.getPostDetail(postSeq);

        // then
        assertNotNull(postDetail);
        System.out.println(postDetail);
    }

    @DisplayName("삭제된 게시글 상세 조회 시 exception 발생 테스트")
    @ParameterizedTest
    @ValueSource(longs = {2L})
    void getDeletedPostDetail(Long postSeq) {

        // then: 예외가 발생하는지 확인
        assertThrows(RestApiException.class, () -> {
            // when: 삭제된 게시글을 조회하려고 시도
            PostDetailResponse postDetail = postQueryService.getPostDetail(postSeq);}
        );
    }

    @DisplayName("게시판 별 인기 태그 조회")
    @ParameterizedTest
    @ValueSource(longs = {1L, 2L})
    void getPopularTag(Long boardSeq) {

        // when
        PopularTagResponse popularTags = postQueryService.getPopularTag(boardSeq);

        // then
        assertFalse(popularTags.getTags().isEmpty());    // 조회된 인기태그가 0건이 아님을 확인
        System.out.println(popularTags);                            // 인기 태그 출력

    }

    @DisplayName("태그 작성 시 관련 인기 키워드 제시")
    @Test
    void getTagKeywords() {
        // given: 검색할 태그 키워드 "맛집"
        String tag = "맛집";

        // when: 서비스 메소드를 호출하여 태그 키워드를 검색
        PopularTagResponse popularTagResponse = postQueryService.getTagKeywords(tag);

        // then: 검색 결과 태그 리스트가 비어 있지 않은지, 예상된 키워드가 포함되어 있는지 확인
        assertFalse(popularTagResponse.getTags().isEmpty(), "태그 리스트는 비어있지 않아야 합니다.");
        // 검색된 모든 태그에 "맛집"이라는 단어가 포함되어 있는지 확인
        boolean allTagsContainKeyword = popularTagResponse.getTags().stream()
                .allMatch(foundTag -> foundTag.contains(tag));

        assertTrue(allTagsContainKeyword, "'맛집'이라는 키워드가 포함된 태그가 있어야 합니다.");

        // 출력 결과 확인
        System.out.println("인기 태그 목록: " + popularTagResponse.getTags());
    }
}