package com.matzip.matzipuser.review.command.infrastructure.repository;

import com.matzip.matzipuser.review.command.domain.aggregate.ReviewImage;
import com.matzip.matzipuser.review.command.domain.repository.ReviewImageRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaReviewImageRepository extends ReviewImageRepository, JpaRepository<ReviewImage, Long> {
}
