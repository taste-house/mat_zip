package com.matzip.matzipback.like.command.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListLikeReqDTO {

    private Long likeUserSeq;
    @NotNull
    private Long listSeq;
}
