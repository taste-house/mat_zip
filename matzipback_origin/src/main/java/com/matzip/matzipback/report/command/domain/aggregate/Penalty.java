package com.matzip.matzipback.report.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "penalty")
@EntityListeners(AuditingEntityListener.class)
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE penalty SET penalty_end_date = NOW() WHERE penalty_seq = ?")
public class Penalty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long penaltySeq;

    private Long penaltyUserSeq;

    @CreatedDate
    private LocalDateTime penaltyStartDate;
    private LocalDateTime penaltyEndDate;
    private String penaltyType;
    private String penaltyReasonContent;


    public void updatePenaltyDetails(LocalDateTime penaltyStartDate, LocalDateTime penaltyEndDate, String penaltyType, String penaltyReasonContent) {
        this.penaltyStartDate = penaltyStartDate;
        this.penaltyEndDate = penaltyEndDate;
        this.penaltyType = penaltyType;
        this.penaltyReasonContent = penaltyReasonContent;
    }
}
