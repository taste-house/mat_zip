package com.matzip.matzipback.matzipList.command.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateListRequest {

    @NotBlank(message = "리스트 제목이 Blank이면 안됩니다.")
    private final String listTitle;
    @NotBlank(message = "리스트 내용이 Blank이면 안됩니다.")
    private final String listContent;

}
