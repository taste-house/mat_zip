package com.matzip.matzipback.review.query.service;

import com.matzip.matzipback.review.query.dto.*;
import com.matzip.matzipback.review.query.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewMapper reviewMapper;

    // 리뷰 검색 및 조회
    @Transactional(readOnly = true)
    public ReviewListResponse getReviews(
            Integer page,
            Integer size,
            String userNickname,
            String reviewContent,
            String restaurantTitle) {
        int offset = (page - 1) * size;
        List<ReviewListDto> reviews = reviewMapper.selectReviews(offset, size, userNickname, reviewContent, restaurantTitle);

        long totalItems = reviewMapper.countReviews(userNickname, reviewContent, restaurantTitle);

        return ReviewListResponse.builder()
                .reviews(reviews)
                .currentPage(page)
                .totalPages((int) Math.ceil((double) totalItems / size))
                .totalItems(totalItems)
                .build();
    }

    // 리뷰 상세 조회
    @Transactional(readOnly = true)
    public ReviewDetailResponse getReview(Long reviewSeq) {

        return reviewMapper.selectReview(reviewSeq);
    }

    // 리뷰 이미지 로드
    public ReviewImageResponse getReviewImages(Long reviewSeq) {
        ReviewImageResponse reviewImages = new ReviewImageResponse();
        reviewImages.setReviewImages(reviewMapper.selectReviewImages(reviewSeq));

        return reviewImages;
    }
}