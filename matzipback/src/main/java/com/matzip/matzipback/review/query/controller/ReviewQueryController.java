package com.matzip.matzipback.review.query.controller;

import com.matzip.matzipback.review.query.dto.ReviewResponse;
import com.matzip.matzipback.review.query.service.ReviewQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ReviewQueryController {

    private final ReviewQueryService reviewQueryService;

    // 리뷰 검색 및 조회
    @GetMapping("/review")
    public ResponseEntity<ReviewResponse> getReview(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String user,
            @RequestParam(required = false) String content,
            @RequestParam(required = false) String restaurant) {

        ReviewResponse response = reviewQueryService.getReviews(page, size, user, content, restaurant);

        return ResponseEntity.ok(response);
    }
}
