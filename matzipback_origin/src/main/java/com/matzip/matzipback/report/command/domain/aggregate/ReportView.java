package com.matzip.matzipback.report.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.Immutable;


import java.time.LocalDateTime;

@Entity
@ToString
@Getter
@Table(name = "report_view")
@Immutable  // 읽기 전용으로 설정
public class ReportView {

    @Id
    private Long reportSeq;
    private String category;
    private Long seq;
    private Long reporterUserSeq;
    private Long reportedUserSeq;
    private String reportContent;
    private Long penaltySeq;
    private String reportStatus;    // wait, none, penalty
    private LocalDateTime reportTime;
    private LocalDateTime reportFinishedTime;

}
