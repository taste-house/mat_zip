package com.matzip.matzipback.like.command.application.service;

import com.matzip.matzipback.like.command.application.dto.ReviewLikeDTO;
import com.matzip.matzipback.like.command.domain.aggregate.Like;
import com.matzip.matzipback.like.command.domain.service.ReviewLikeDomainService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewLikeService {

    private final ReviewLikeDomainService reviewLikeDomainService;
    private final ModelMapper modelMapper;

    public boolean saveReviewLike(Long userSeq, Long reviewSeq) {

        Optional<Like> foundReivewLike = reviewLikeDomainService.findLikeByUserSeqAndReviewSeq(
                userSeq,
                reviewSeq
        );

        if (foundReivewLike.isEmpty()) {
            Like newReviewLike = modelMapper.map(new ReviewLikeDTO(userSeq, reviewSeq), Like.class);
            reviewLikeDomainService.save(newReviewLike);
            return true;
        } else {
            reviewLikeDomainService.deleteById(foundReivewLike.get().getLikeSeq());
            return false;
        }
    }
}
