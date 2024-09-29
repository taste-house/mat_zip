package com.matzip.matzipback.review.query.service;

import com.matzip.matzipback.review.query.dto.ReviewDto;
import com.matzip.matzipback.review.query.dto.ReviewResponse;
import com.matzip.matzipback.review.query.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewMapper reviewMapper;

    //  리뷰 검색 및 조회
    public ReviewResponse getReviews(
            Integer page,
            Integer size,
            String userNickname,
            String reviewContent,
            String restaurantTitle) {
        int offset = (page - 1) * size;
        List<ReviewDto> reviews = reviewMapper.selectReviews(offset, size, userNickname, reviewContent, restaurantTitle);

        long totalItems = reviewMapper.countReviews(userNickname, reviewContent, restaurantTitle);

        return ReviewResponse.builder()
                .reviews(reviews)
                .currentPage(page)
                .totalPages((int) Math.ceil((double) totalItems / size))
                .totalItems(totalItems)
                .build();
    }
}
