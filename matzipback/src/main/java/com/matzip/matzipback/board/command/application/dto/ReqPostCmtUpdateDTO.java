package com.matzip.matzipback.board.command.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqPostCmtUpdateDTO {

    private Long postCommentSeq;
    private String postCommentContent;
}
