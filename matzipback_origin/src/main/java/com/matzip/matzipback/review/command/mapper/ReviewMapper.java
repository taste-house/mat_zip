package com.matzip.matzipback.review.command.mapper;

import com.matzip.matzipback.review.command.application.dto.ReviewCreateRequest;
import com.matzip.matzipback.review.command.domain.aggregate.Review;

import java.math.BigDecimal;

public class ReviewMapper {
    public static Review toEntity(Long authUserSeq, ReviewCreateRequest reviewRequest) {
        return Review.create(
                authUserSeq,
                reviewRequest.getRestaurantSeq(),
                reviewRequest.getReviewContent(),
                reviewRequest.getReviewStar()
        );
    }
}
