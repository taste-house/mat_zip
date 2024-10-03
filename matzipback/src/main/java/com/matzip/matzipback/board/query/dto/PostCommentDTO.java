package com.matzip.matzipback.board.query.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class PostCommentDTO {
    private Long postCommentSeq;
    private String userNickname;
    private String postCommentContent;
    private LocalDateTime createdTime;
    private LocalDateTime lastUpdateTime;

}
