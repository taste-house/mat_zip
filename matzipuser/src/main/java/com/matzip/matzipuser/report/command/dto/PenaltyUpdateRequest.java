package com.matzip.matzipuser.report.command.dto;

import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class PenaltyUpdateRequest {

    @FutureOrPresent(message = "시작일은 오늘 이후로 설정할 수 있습니다.")
    private LocalDateTime penaltyStartDate;     // 패널티 시작일

    @FutureOrPresent(message = "종료일 설정이 잘못 되었습니다.")
    private LocalDateTime penaltyEndDate;       // 패널티 종료일

    private String penaltyType;                 // 패널티 종류(정지 or 영구정지)

    private String penaltyReasonContent;        // 패널티 사유(생략 가능)
}
