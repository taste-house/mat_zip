package com.matzip.matzipback.board.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BoardLikeDTO {

    private long userSeq;
    private long boardCategorySeq;
}
