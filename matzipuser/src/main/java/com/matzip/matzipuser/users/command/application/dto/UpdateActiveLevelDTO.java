package com.matzip.matzipuser.users.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateActiveLevelDTO {

    private Long activeLevelSeq;
    private String activeLevelName;
    private Integer activeLevelStandard;
}
