package com.matzip.matzipback.report.command.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostReportResDTO {

    // saveCode = 0 : 실패
    // saveCode = 1 : 성공
    int saveCode;
    String message;
}
