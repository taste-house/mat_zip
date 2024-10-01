package com.matzip.matzipback.like.command.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ListCmtLikeReqDTO {

    private Long likeUserSeq;
    @NotNull
    private Long listCommentSeq;

}
