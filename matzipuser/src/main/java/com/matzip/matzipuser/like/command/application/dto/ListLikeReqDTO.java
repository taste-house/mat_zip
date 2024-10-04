package com.matzip.matzipuser.like.command.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListLikeReqDTO {

    private Long likeUserSeq;
    @NotNull(message = "리스트 고유번호가 null이면 안됩니다.")
    private Long listSeq;
}
