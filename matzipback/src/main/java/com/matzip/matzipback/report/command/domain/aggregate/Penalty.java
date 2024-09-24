package com.matzip.matzipback.report.command.domain.aggregate;

import com.matzip.matzipback.users.command.domain.aggregate.Users;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "penalty")
@NoArgsConstructor/*(access = AccessLevel.PROTECTED)*/
@SQLDelete(sql = "UPDATE penalty SET penalty_end_date = NOW() WHERE penalty_seq = ?")
public class Penalty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long penaltySeq;

    private Long  penaltyUserSeq;

    @CreatedDate
    private LocalDateTime penalty_start_date;
    private LocalDateTime penalty_end_date;
    private String penalty_type;
    private String penalty_reason_content;


}
