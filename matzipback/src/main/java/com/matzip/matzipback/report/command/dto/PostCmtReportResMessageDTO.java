package com.matzip.matzipback.report.command.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostCmtReportResMessageDTO {

    private int code;
    private String message;
    private Long postSeq;
}
