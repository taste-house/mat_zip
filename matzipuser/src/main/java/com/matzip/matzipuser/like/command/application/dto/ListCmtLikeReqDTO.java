package com.matzip.matzipuser.like.command.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListCmtLikeReqDTO {

    private Long likeUserSeq;
    @NotNull
    private Long listCommentSeq;

}
