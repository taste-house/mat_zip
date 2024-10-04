package com.matzip.matzipuser.review.command.infrastructure.repository;

import com.matzip.matzipuser.review.command.domain.aggregate.Review;
import com.matzip.matzipuser.review.command.domain.repository.ReviewRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaReviewRepository extends ReviewRepository, JpaRepository<Review, Long> {
}
