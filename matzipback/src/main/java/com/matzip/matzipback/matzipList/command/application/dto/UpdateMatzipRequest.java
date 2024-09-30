package com.matzip.matzipback.matzipList.command.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UpdateMatzipRequest {
    @NotNull
    private final Long listSeq;
    @NotNull
    private final Long listMatzipSeq;
    @NotNull
    private final Long restaurantSeq;
    private final String listMatzipComment;
}
