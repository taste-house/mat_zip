package com.matzip.matzipback.board.query.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostCommentDTO {
    private Long postCommentSeq;
    private String userNickname;
    private String postCommentContent;
    private LocalDateTime postCommentCreatedTime; //작성 시간
    private LocalDateTime postCommentUpdatedTime; //최종 수정 시간
}
