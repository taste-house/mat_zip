package com.matzip.matzipuser.matzipList.command.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateListCmtRequest {

    @NotBlank
    private final Long listSeq;
    @NotBlank
    private final String listCommentContent;
}
