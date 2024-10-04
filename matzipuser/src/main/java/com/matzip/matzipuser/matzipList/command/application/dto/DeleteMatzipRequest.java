package com.matzip.matzipuser.matzipList.command.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DeleteMatzipRequest {

    @NotNull(message = "리스트 맛집 고유번호가 Null이면 안됩니다.")
    private Long listMatzipSeq;

    public void setListMatzipSeq(long listMatzipSeq) {
        this.listMatzipSeq = listMatzipSeq;
    }
}
