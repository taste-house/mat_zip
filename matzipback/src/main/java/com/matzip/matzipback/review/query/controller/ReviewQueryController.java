package com.matzip.matzipback.review.query.controller;

import com.matzip.matzipback.review.query.dto.ReviewByRestaurantResponse;
import com.matzip.matzipback.review.query.dto.ReviewDetailResponse;
import com.matzip.matzipback.review.query.dto.ReviewImageResponse;
import com.matzip.matzipback.review.query.dto.ReviewListResponse;
import com.matzip.matzipback.review.query.service.ReviewQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Tag(name = "Review", description = "리뷰")
public class ReviewQueryController {

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/review")
    @Operation(summary = "리뷰 검색 및 조회", description = "리뷰를 검색 및 조회한다.")
    public ResponseEntity<ReviewListResponse> getReview(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String user,
            @RequestParam(required = false) String content,
            @RequestParam(required = false) String restaurant) {

        ReviewListResponse response = reviewQueryService.getReviews(page, size, user, content, restaurant);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/review/{reviewSeq}")
    @Operation(summary = "리뷰 상세 조회", description = "특정 리뷰를 조회한다.")
    public ResponseEntity<ReviewDetailResponse> getReview(@PathVariable Long reviewSeq) {

        ReviewDetailResponse response = reviewQueryService.getReview(reviewSeq);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/review/{reviewSeq}/image")
    @Operation(summary = "리뷰 이미지 로드", description = "특정 리뷰의 이미지를 로드한다.")
    public ResponseEntity<ReviewImageResponse> getReviewImages(@PathVariable Long reviewSeq) {

        ReviewImageResponse response = reviewQueryService.getReviewImages(reviewSeq);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/review/restaurant/{restaurantSeq}")
    @Operation(summary = "음식점 별 리뷰 조회", description = "음식점 별 리뷰를 조회한다.")
    public ResponseEntity<ReviewByRestaurantResponse> getReviewsByRestaurant(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @PathVariable Long restaurantSeq) {

        ReviewByRestaurantResponse response = reviewQueryService.getReviewsByRestaurant(page, size, restaurantSeq);

        return ResponseEntity.ok(response);
    }
}
