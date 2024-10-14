package com.matzip.matzipback.like.command.domain.service;

import com.matzip.matzipback.exception.RestApiException;
import com.matzip.matzipback.like.command.domain.aggregate.Like;
import com.matzip.matzipback.like.command.domain.repository.ReviewLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

import static com.matzip.matzipback.exception.ErrorCode.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ReviewLikeDomainService {

    private final ReviewLikeRepository reviewLikeRepository;

    public Optional<Like> findLikeByUserSeqAndReviewSeq(Long userSeq, Long reviewSeq) {
        return reviewLikeRepository.findByLikeUserSeqAndReviewSeq(userSeq, reviewSeq);
    }

    public void save(Like like) { reviewLikeRepository.save(like); }

    public void deleteById(Long userSeq) { reviewLikeRepository.deleteById(userSeq); }
}
