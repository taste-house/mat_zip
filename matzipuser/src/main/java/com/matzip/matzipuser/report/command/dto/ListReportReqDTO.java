package com.matzip.matzipuser.report.command.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ListReportReqDTO {

    private Long listSeq;
    private String reportContent;


}
