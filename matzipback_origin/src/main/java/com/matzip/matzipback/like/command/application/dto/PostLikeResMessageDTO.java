package com.matzip.matzipback.like.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostLikeResMessageDTO {

    private int code;
    private String message;
    private long postSeq;
}
