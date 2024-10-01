package com.matzip.matzipback.review.command.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public class ReviewCreateRequest {

    @NotBlank
    private final Long restaurantSeq;
    @NotBlank
    private final String reviewContent;
    @NotBlank
    private final BigDecimal reviewStar;
}
