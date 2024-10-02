package com.matzip.matzipback.report.command.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PtAndCmtReportReqDTO {

    private Long reporterUserSeq;
    private Long reportedUserSeq;
    private Long postSeq;
    private Long postCommentSeq;
    private String reportContent;
}
