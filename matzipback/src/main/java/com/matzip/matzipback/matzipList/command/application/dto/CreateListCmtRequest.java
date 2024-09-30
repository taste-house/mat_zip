package com.matzip.matzipback.matzipList.command.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateListCmtRequest {

    @NotNull
    private final Long listSeq;
    @NotBlank
    private final String listCommentContent;
}
