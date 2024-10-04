package com.matzip.matzipuser.report.query.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
public class ReportDTO {
    private Long reportSeq;
    private String category;
    private Long seq;
    private Long reporterUserSeq;
    private String reporterUserName;
    private Long reportedUserSeq;
    private String reportedUserName;
    private String reportContent;
    private List<ReasonDTO> reasons;
    private String penaltySeq;
    private String reportStatus;
    private LocalDateTime reportTime;
    private LocalDateTime reportFinishedTime;
}
