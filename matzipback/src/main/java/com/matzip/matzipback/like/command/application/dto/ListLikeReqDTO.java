package com.matzip.matzipback.like.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListLikeReqDTO {

    private Long likeUserSeq;
    private Long listSeq;
}
