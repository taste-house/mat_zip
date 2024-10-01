package com.matzip.matzipback.board.command.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqPostCmtCreateDTO {

    @NotBlank
    private Long postSeq;
    private Long PostCommentUserSeq;
    @NotBlank
    private String postCommentContent;

}
