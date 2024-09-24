package com.matzip.matzipback.board.command.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqPostCmtCreateDTO {

    private Long postSeq;
    private String postCommentContent;

}
