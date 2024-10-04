package com.matzip.matzipuser.board.command.application.dto;

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
