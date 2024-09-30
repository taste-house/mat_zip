package com.matzip.matzipback.matzipList.command.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
public class UpdateListRequset {
    @NotNull
    private Long listSeq;
    @NotBlank
    private String listTitle;
    @NotBlank
    private String listContent;


}
