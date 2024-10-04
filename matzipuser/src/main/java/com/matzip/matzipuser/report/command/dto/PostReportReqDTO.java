package com.matzip.matzipuser.report.command.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostReportReqDTO {
    private Long postSeq;
    private String reportContent;
}
