package com.matzip.matzipuser.like.command.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCmtLikeReqDTO {

    private Long likeUserSeq;
    private Long postCommentSeq;
}
