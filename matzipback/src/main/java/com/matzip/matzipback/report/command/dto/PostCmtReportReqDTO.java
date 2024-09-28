package com.matzip.matzipback.report.command.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostCmtReportReqDTO {
    private Long postCommentSeq;
    private String reportContent;
}
