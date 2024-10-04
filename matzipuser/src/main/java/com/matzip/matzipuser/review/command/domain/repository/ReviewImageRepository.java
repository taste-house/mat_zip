package com.matzip.matzipuser.review.command.domain.repository;

import com.matzip.matzipuser.review.command.domain.aggregate.ReviewImage;

public interface ReviewImageRepository {
    ReviewImage save(ReviewImage reviewImage);
}
