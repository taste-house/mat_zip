package com.matzip.matzipuser.matzipList.command.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UpdateMatzipRequest {
    @NotNull(message = "리스트 고유번호가 Null이면 안됩니다.")
    private final Long listSeq;
    @NotNull(message = "리스트 맛집 고유번호가 Null이면 안됩니다.")
    private final Long listMatzipSeq;
    @NotNull(message = "음식점 고유번호가 Null이면 안됩니다.")
    private final Long restaurantSeq;
    private final String listMatzipComment;
}
