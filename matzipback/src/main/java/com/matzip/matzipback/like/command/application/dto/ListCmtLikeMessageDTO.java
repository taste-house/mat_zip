package com.matzip.matzipback.like.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class ListCmtLikeMessageDTO {

    private int code;
    private String message;
    private Long listCommentSeq;
}
