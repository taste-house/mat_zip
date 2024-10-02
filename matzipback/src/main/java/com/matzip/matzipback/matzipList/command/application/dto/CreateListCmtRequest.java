package com.matzip.matzipback.matzipList.command.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateListCmtRequest {

    @Null(message = "유저 정보를 넣으시면 안됩니다.")
    private long listCommentUserSeq;
    @NotNull(message = "리스트 번호에 아무 값도 넣지 않으면 안됩니다.")
    private Long listSeq; // 리스트 번호
    @NotBlank(message = "댓글 내용에 아무 내용도 적지 않으면 안됩니다.")
    private String listCommentContent; // 리스트 댓글 내용
}
