package com.matzip.matzipuser.review.query.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

// 리뷰 상세 조회 DTO 그대로 반환
@Getter @Setter
public class ReviewDetailResponse {
    private Long reviewSeq;
    private String reviewUserSeq;
    private String userNickname;
    private String restaurantSeq;
    private String restaurantTitle;
    private String reviewContent;
    private LocalDateTime reviewCreatedTime;
    private LocalDateTime reviewUpdatedTime;
    private String reviewStar;
}
