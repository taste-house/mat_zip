package com.matzip.matzipuser.like.command.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostLikeReqDTO {

    private long postSeq;
    private Long likeUserSeq;
}
