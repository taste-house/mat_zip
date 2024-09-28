package com.matzip.matzipback.board.command.domain.service;

import com.matzip.matzipback.board.command.domain.aggregate.PostComment;
import com.matzip.matzipback.board.command.domain.repository.PostCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostCommentDomainService {

    private final PostCommentRepository postCommentRepository;

    // postCommentSeq 로 postComment 조회
    public PostComment findByPostCommentSeq(Long postCommentSeq) {
        return postCommentRepository.findById(postCommentSeq).orElse(null);
    }

}
