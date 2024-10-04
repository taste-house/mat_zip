package com.matzip.matzipuser.matzipList.command.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
public class UpdateListRequest {
    @NotNull(message = "리스트 고유번호가 Null이면 안됩니다.")
    private Long listSeq;
    @NotBlank(message = "리스트 제목이 Blank이면 안됩니다.")
    private String listTitle;
    @NotBlank(message = "리스트 내용이 Blank이면 안됩니다.")
    private String listContent;


    public UpdateListRequest(long listSeq, String listTitle, String listContent) {
        this.listSeq = listSeq;
        this.listTitle = listTitle;
        this.listContent = listContent;
    }
}
