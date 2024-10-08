package com.matzip.matzipback.review.command.infrastructure.repository;

import com.matzip.matzipback.review.command.domain.aggregate.ReviewImage;
import com.matzip.matzipback.review.command.domain.repository.ReviewImageRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaReviewImageRepository extends ReviewImageRepository, JpaRepository<ReviewImage, Long> {
}
