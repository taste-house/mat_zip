package com.matzip.matzipback.matzipList.command.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateListRequest {

    @NotBlank
    private final String listTitle;
    @NotBlank
    private final String listContent;

}
