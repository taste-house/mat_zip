package com.matzip.matzipuser.board.command.application.service;

import com.matzip.matzipuser.board.command.application.dto.PostAndTagRequestDTO;
import com.matzip.matzipuser.exception.RestApiException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional // 테스트 후 DB에 반영되지 않고 롤백
class PostCommandServiceTest {

    @Autowired
    private PostCommandService postCommandService;

    // 게시글 등록 테스트 데이터 제공 메소드
    private static Stream<Arguments> newPost() {
        return Stream.of(
                Arguments.of(new PostAndTagRequestDTO("Post Title 1", "Post Content 1", 1L, 1L, 1L, Arrays.asList("tag1", "tag2")))
        );
    }

    // 게시글 수정 테스트 데이터 제공 메소드
    private static Stream<Arguments> updatePost() {
        return Stream.of(
                Arguments.of(1L, new PostAndTagRequestDTO("Updated Title 1", "Updated Content 1", 1L, 1L, 1L, Arrays.asList("tag3", "tag4")))
        );
    }

    @DisplayName("게시글 등록 테스트")
    @ParameterizedTest
    @MethodSource("newPost")
    void createPost(PostAndTagRequestDTO newPost) {
        // when: PostCommandService를 사용하여 게시글 등록
        // userSeq 를 하드코딩한 코드로 테스트해야함
        Long postSeq = postCommandService.createPost(newPost);

        // then: 생성된 게시글의 seq가 null이 아닌지 확인
        System.out.println(postSeq);
        assertNotNull(postSeq, "게시글이 성공적으로 등록되었습니다.");
    }

    @DisplayName("게시글 수정 테스트")
    @ParameterizedTest
    @MethodSource("updatePost")
    void updatePost(Long postSeq, PostAndTagRequestDTO updatedPost) {
        // when: PostCommandService를 사용하여 게시글 수정
        postCommandService.updatePost(postSeq, updatedPost);

        // then: 성공적으로 수정되었는지 확인
        assertDoesNotThrow(() -> postCommandService.updatePost(postSeq, updatedPost), "게시글이 성공적으로 수정되었습니다.");
    }

    @DisplayName("게시글 소프트 삭제 테스트")
    @ParameterizedTest
    @ValueSource(longs ={34L})
    void deletePost(Long postSeq) {
        // when: PostCommandService를 사용하여 게시글 소프트 삭제
        postCommandService.deletePost(postSeq);

        // then: 삭제 후 예외가 발생하는지 확인
        // 첫 번째 deletePost 호출이 소프트 삭제를 수행하고, 두 번째 호출이 해당 게시글을 찾지 못해 NOT_FOUND 예외 발생
        assertThrows(RestApiException.class, () -> postCommandService.deletePost(postSeq), "게시글이 이미 삭제된 상태입니다.");
    }

}