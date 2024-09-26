package com.matzip.matzipback.board.command.application.service;

import com.matzip.matzipback.board.command.domain.aggregate.Post;
import com.matzip.matzipback.board.command.domain.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class PostCommandService {

    private final PostRepository postRepository;

    /* 1. 게시글 등록 */

    /* 2. 게시글 수정 */

    /* 3. 게시글 삭제*/
    public Long deletePost(Long postSeq) {

        // 전달 된 postSeq로 Post Entity 조회
        Post post = postRepository.findById(postSeq)
                // 조회 된 Post 엔티티가 없을 경우
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + postSeq));

        // getter 메소드를 통해 반환해 줄 boardCategorySeq 값 삽입
        Long boardCategorySeq = post.getBoardCategorySeq();

        // 게시글 Soft Delete
        postRepository.deleteById(postSeq);

        return boardCategorySeq;

    }

}
