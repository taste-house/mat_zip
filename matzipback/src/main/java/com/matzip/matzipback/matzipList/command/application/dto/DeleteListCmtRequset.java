package com.matzip.matzipback.matzipList.command.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DeleteListCmtRequset {
    private Long listSeq;
    @NotBlank
    private Long listCommentSeq;
    private Long listCommentListSeq;
}
