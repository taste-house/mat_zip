package com.matzip.matzipback.board.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ResPostCmtDTO {

    private Long postCommentSeq;
    private LocalDateTime postCommentCreatedTime;

}
