package com.matzip.matzipback.like.command.application.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCmtLikeReqDTO {

    @Null(message = "좋아요를 누르는 사용자 정보는 필요없습니다.")
    private Long likeUserSeq;
    @NotNull(message = "게시글 댓글 번호를 넣어주세요.")
    private Long postCommentSeq;
}
