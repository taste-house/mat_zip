package com.matzip.matzipuser.review.command.mapper;

import com.matzip.matzipuser.review.command.application.dto.ReviewCreateRequest;
import com.matzip.matzipuser.review.command.domain.aggregate.Review;

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
