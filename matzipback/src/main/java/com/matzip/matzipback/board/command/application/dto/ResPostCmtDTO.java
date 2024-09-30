package com.matzip.matzipback.board.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResPostCmtDTO {

    private int code;
    private String message;
    private Long postSeq;
}
