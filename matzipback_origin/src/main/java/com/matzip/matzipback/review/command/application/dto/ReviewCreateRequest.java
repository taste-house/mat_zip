package com.matzip.matzipback.review.command.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public class ReviewCreateRequest {

    @NotNull
    private final Long restaurantSeq;
    @NotBlank
    private final String reviewContent;
    @NotNull
    private final BigDecimal reviewStar;
}
