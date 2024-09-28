package com.matzip.matzipback.matzipList.command.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateMatzipRequest {

    @NotBlank
    private final Long listSeq;
    @NotBlank
    private final Long restaurantSeq;
    private final String listMatzipComment;

}
