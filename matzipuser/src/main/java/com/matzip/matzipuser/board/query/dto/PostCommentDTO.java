package com.matzip.matzipuser.board.query.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class PostCommentDTO {
    private Long postSeq;
    private String postTitle;
    private String postCommentContent;
    private String userNickname;
    private LocalDateTime postCommentCreatedTime;
    private LocalDateTime postCommentUpdatedTime;

}
