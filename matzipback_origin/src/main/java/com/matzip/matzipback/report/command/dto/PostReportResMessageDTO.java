package com.matzip.matzipback.report.command.dto;

import com.matzip.matzipback.report.command.domain.aggregate.Report;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostReportResMessageDTO {

    private int code;
    private String message;
    private Long postSeq;
}
