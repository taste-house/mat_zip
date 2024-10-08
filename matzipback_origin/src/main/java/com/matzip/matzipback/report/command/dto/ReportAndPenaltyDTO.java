package com.matzip.matzipback.report.command.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ReportAndPenaltyDTO {

    @NotBlank
    private String category;        // 신고된 컨텐츠 종류(리스트,리스트댓글,맛집리뷰,게시글,게시글댓글,쪽지)

    @NotNull
    private Long seq;               // 해당 컨텐츠의 고유번호

    @NotNull
    private Long penaltyUserSeq;    // 패널티 대상 사용자

    private LocalDateTime penaltyStartDate;     // 패널티 시작일

    @NotNull(message = "패널티 종료일을 입력하지 않았습니다.")
    @FutureOrPresent(message = "종료일 설정이 잘못 되었습니다.")
    private LocalDateTime penaltyEndDate;       // 패널티 종료일

    @NotBlank(message = "패널티 종류를 입력하지 않았습니다.")
    private String penaltyType;                 // 패널티 종류(정지 or 영구정지)

    private String penaltyReasonContent;        // 패널티 사유(생략 가능)

}
