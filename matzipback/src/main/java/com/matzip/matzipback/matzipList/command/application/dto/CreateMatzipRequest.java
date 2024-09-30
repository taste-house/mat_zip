package com.matzip.matzipback.matzipList.command.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateMatzipRequest {
    @NotNull
    private final Long listSeq;
    @NotNull
    private final Long restaurantSeq;
    private final String listMatzipComment;
}
