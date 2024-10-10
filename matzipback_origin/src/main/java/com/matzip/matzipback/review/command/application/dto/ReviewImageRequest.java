package com.matzip.matzipback.review.command.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewImageRequest {

    @NotBlank
    private final Long reviewSeq;
    @NotBlank
    private final String reviewImagePath;
}
