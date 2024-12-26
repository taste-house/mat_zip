package com.matzip.matzipback.report.query.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PenaltyDTO {

    private long penaltySeq;
    private long penaltyUserSeq;
    private String userNickname;
    private LocalDateTime penaltyStartDate;
    private LocalDateTime penaltyEndDate;
    private String penaltyType;
    private String penaltyReasonContent;

}
