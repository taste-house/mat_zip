package com.matzip.matzipback.board.command.domain.service;

import com.matzip.matzipback.board.command.domain.aggregate.Post;
import com.matzip.matzipback.board.command.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostDomainService {

    private final PostRepository postRepository;

    // postSeq 로 post 조회
    public Post findByPostSeq(Long postSeq) {
        return postRepository.findById(postSeq).orElse(null);
    }
}
