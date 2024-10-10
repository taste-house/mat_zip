package com.matzip.matzipback.report.command.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ListCmtReportReqDTO {

    private Long listCommentSeq;
    private String reportContent;
}
