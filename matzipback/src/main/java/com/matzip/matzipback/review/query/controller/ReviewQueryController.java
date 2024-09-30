package com.matzip.matzipback.review.query.controller;

import com.matzip.matzipback.review.query.dto.ReviewDetailResponse;
import com.matzip.matzipback.review.query.dto.ReviewImageResponse;
import com.matzip.matzipback.review.query.dto.ReviewListResponse;
import com.matzip.matzipback.review.query.service.ReviewQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ReviewQueryController {

    private final ReviewQueryService reviewQueryService;

    // 리뷰 검색 및 조회
    @GetMapping("/review")
    public ResponseEntity<ReviewListResponse> getReview(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String user,
            @RequestParam(required = false) String content,
            @RequestParam(required = false) String restaurant) {

        ReviewListResponse response = reviewQueryService.getReviews(page, size, user, content, restaurant);

        return ResponseEntity.ok(response);
    }

    // 리뷰 상세 조히
    @GetMapping("/review/{reviewSeq}")
    public ResponseEntity<ReviewDetailResponse> getReview(@PathVariable Long reviewSeq) {

        ReviewDetailResponse response = reviewQueryService.getReview(reviewSeq);

        return ResponseEntity.ok(response);
    }

    // 리뷰 이미지 로드
    @GetMapping("/review/{reviewSeq}/image")
    public ResponseEntity<ReviewImageResponse> getReviewImages(@PathVariable Long reviewSeq) {

        ReviewImageResponse response = reviewQueryService.getReviewImages(reviewSeq);

        return ResponseEntity.ok(response);
    }
}
