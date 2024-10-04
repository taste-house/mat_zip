package com.matzip.matzipuser.review.query.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ReviewListDto {
    private Long reviewSeq;
    private String reviewUserSeq;
    private String userNickname;
    private String restaurantSeq;
    private String restaurantTitle;
    private String reviewImageSeq;
    private String reviewContent;
    private LocalDateTime reviewCreatedTime;
    private String reviewStar;
}
