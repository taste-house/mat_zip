package com.matzip.matzipback.board.command.domain.service;

import com.matzip.matzipback.board.command.domain.aggregate.Post;
import com.matzip.matzipback.board.command.domain.repository.PostDomainRepository;
import com.matzip.matzipback.report.command.domain.aggregate.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostDomainService {

    private final PostDomainRepository postDomainRepository;

    // postSeq 로 post 조회
    public Post findByPostSeq(Long postSeq) {
        return postDomainRepository.findById(postSeq).orElse(null);
    }
}
