package com.matzip.matzipback.like.command.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCmtLikeReqDTO {
    private Long postSeq;
    private Long postCommentSeq;
    private Long likeUserSeq;
}
