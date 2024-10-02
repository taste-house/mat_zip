package com.matzip.matzipback.matzipList.command.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ListBoxUpdateRequest {

    @NotNull
    private Long listSeq;
    @NotNull
    private Long listUserSeq;
    @NotNull
    private int listLevel;
}
