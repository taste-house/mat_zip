package com.matzip.matzipback.matzipList.command.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateMatzipRequest {
    @NotNull(message = "리스트 고유번호가 Null이면 안됩니다.")
    private final Long listSeq;
    @NotNull(message = "음식점 고유번호가 Null이면 안됩니다.")
    private final Long restaurantSeq;
    private final String listMatzipComment;
}
