package com.matzip.matzipback.review.query.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ReviewImageResponse {
    private List<ReviewImageDto> reviewImages;
}
