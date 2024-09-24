package com.matzip.matzipback.report.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "report_reason")
@NoArgsConstructor/*(access = AccessLevel.PROTECTED)*/
public class ReportReason {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  reportReasonSeq;

    private Long  reportSeq;
    private Long  reasonSeq;


    /*@ManyToOne
    @JoinColumn(name = "report_seq", referencedColumnName = "reportSeq")
    private Report report; // 신고와의 관계

    @ManyToOne
    @JoinColumn(name = "reason_code", referencedColumnName = "reasonCode")
    private Reason reasonCode; // 신고 사유 코드*/

}
