package com.matzip.matzipuser.like.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ListCmtLikeMessageDTO {

    private int code;
    private String message;
    private Long listCommentSeq;
}
