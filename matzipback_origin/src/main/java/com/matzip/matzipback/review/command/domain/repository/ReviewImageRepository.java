package com.matzip.matzipback.review.command.domain.repository;

import com.matzip.matzipback.review.command.domain.aggregate.ReviewImage;

public interface ReviewImageRepository {
    ReviewImage save(ReviewImage reviewImage);
}
