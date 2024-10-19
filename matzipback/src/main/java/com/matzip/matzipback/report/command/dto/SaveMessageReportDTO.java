package com.matzip.matzipback.report.command.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SaveMessageReportDTO {

    @NotNull
    private Long reporterUserSeq; // 신고한 사람
    @NotNull
    private Long reportedUserSeq; // 신고 당한 사람
    private String reportContent;
    @NotNull
    private Long messageSeq;
}
