package com.matzip.matzipback.matzipList.command.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UpdateMatzipRequest {
    @NotBlank
    private final Long listSeq;
    @NotBlank
    private final Long listMatzipSeq;
    @NotBlank
    private final Long restaurantSeq;
    private final String listMatzipComment;
}
