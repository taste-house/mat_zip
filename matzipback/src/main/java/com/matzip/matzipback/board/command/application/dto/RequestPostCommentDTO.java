package com.matzip.matzipback.board.command.application.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
public class RequestPostCommentDTO {

    private Long postSeq;
    private String postCommentContent;

}
