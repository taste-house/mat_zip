package com.matzip.matzipback.review.command.application.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public class ReviewUpdateRequest {

    @NotBlank(message = "내용을 작성하지 않았습니다.")
    private final String reviewContent;
    @NotNull(message = "별점을 선택하지 않았습니다.")
    @DecimalMin(value = "1.00", message = "1보다 낮은 별점을 부여할 수 없습니다.")
    @DecimalMax(value = "5.00", message = "5보다 높은 별점을 부여할 수 없습니다.")
    private final BigDecimal reviewStar;
}
