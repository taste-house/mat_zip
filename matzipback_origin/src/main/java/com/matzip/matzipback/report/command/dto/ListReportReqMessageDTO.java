package com.matzip.matzipback.report.command.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ListReportReqMessageDTO {

    private int code;
    private String message;
    private Long listSeq;

}
