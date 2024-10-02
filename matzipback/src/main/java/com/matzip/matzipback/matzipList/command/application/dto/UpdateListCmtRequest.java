package com.matzip.matzipback.matzipList.command.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateListCmtRequest {

    @NotNull(message = "수정하려는 댓글 번호를 넣어주세요.")
    private final Long listCommentSeq;
    @NotBlank(message = "수정할 내용이 빈 값이면 안됩니다.")
    private final String listCommentContent;
}
