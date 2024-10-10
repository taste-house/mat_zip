package com.matzip.matzipback.report.command.domain.aggregate;

import com.matzip.matzipback.report.command.dto.ListCmtReportReqDTO;
import com.matzip.matzipback.report.command.dto.ListReportReqDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "report")
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportSeq;

    @Column(nullable = false)
    private Long reporterUserSeq;
    @Column(nullable = false)
    private Long reportedUserSeq;
    private Long penaltySeq;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime reportTime;
    private String reportContent;
    private LocalDateTime reportFinishedTime;
    @Column(nullable = false)
    @ColumnDefault("wait") // 기본값 지정
    private String reportStatus = "wait"; // wait, none, penalty

    private Long postSeq;
    private Long postCommentSeq;
    private Long listSeq;
    private Long listCommentSeq;
    private Long messageSeq;
    private Long reviewSeq;



    private Report(Long reporterUserSeq, Long reportedUserSeq, ListReportReqDTO listReportReqDTO) {
        this.reporterUserSeq = reporterUserSeq;
        this.reportedUserSeq = reportedUserSeq;
        this.listSeq = listReportReqDTO.getListSeq();
        this.reportContent = listReportReqDTO.getReportContent();
    }

    private Report(Long reporterUserSeq, Long reportedUserSeq, ListCmtReportReqDTO listCmtReportReqDTO) {
        this.reporterUserSeq = reporterUserSeq;
        this.reportedUserSeq = reportedUserSeq;
        this.listCommentSeq = listCmtReportReqDTO.getListCommentSeq();
        this.reportContent = listCmtReportReqDTO.getReportContent();
    }

    public static Report getListReportSeq(Long reporterUserSeq, Long reportedUserSeq, ListReportReqDTO listReportReqDTO) {
        return new Report(reporterUserSeq, reportedUserSeq, listReportReqDTO);
    }

    public static Report getListCmtReportSeq(Long reporterUserSeq, Long reportedUserSeq, ListCmtReportReqDTO listCmtReportReqDTO) {
        return new Report(reporterUserSeq, reportedUserSeq, listCmtReportReqDTO);
    }

    public void updateReportDetails(Long penaltySeq) {
        this.penaltySeq = penaltySeq;
        this.reportFinishedTime = LocalDateTime.now();
        this.reportStatus = "penalty";
    }

    /*@ManyToOne
    @JoinColumn(name = "post_seq")
    private Post postSeq; // 게시글과의 관계

    @ManyToOne
    @JoinColumn(name = "post_comment_seq")
    private PostComment postCommentSeq; // 게시글 댓글과의 관계

    @ManyToOne
    @JoinColumn(name = "list_seq")
    private MyList listSeq; // 리스트와의 관계

    @ManyToOne
    @JoinColumn(name = "list_comment_seq")
    private MyListComment listCommentSeq; // 리스트 댓글과의 관계\

    @ManyToOne
    @JoinColumn(name = "message_seq")
    private Messages messageSeq;

    @ManyToOne
    @JoinColumn(name = "review_seq")
    private Review reviewSeq;*/

}
