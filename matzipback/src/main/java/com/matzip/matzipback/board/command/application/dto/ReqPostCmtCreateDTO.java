package com.matzip.matzipback.board.command.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqPostCmtCreateDTO {

    private Long postCommentUserSeq;
    private Long postSeq;
    @NotBlank
    private String postCommentContent;

}
