package com.matzip.matzipback.matzipList.command.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DeleteListRequest {

    @NotNull(message = "리스트 고유번호가 Null이면 안됩니다.")
    private Long listSeq;

    public DeleteListRequest(@NotNull Long listSeq) {
        this.listSeq = listSeq;
    }
}
