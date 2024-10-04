package com.matzip.matzipuser.like.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostCmtLikeResMessageDTO {

    private int code;
    private String message;
    private long postSeq;
}
