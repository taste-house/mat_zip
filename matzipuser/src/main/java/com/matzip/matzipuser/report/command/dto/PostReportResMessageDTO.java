package com.matzip.matzipuser.report.command.dto;

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
