package com.matzip.matzipback.matzipList.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "review")
@NoArgsConstructor
@SQLDelete(sql = "UPDATE review SET review_status = 'delete', review_deleted_time = NOW() WHERE review_seq = ?")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewSeq;
    private int reviewUserSeq;
    private int restaurantSeq;
    private String reviewContent;
    private String reviewStatus;
    @CreatedDate
    private LocalDateTime reviewCreatedTime;
    @LastModifiedDate
    private LocalDateTime reviewUpdatedTime;
    private LocalDateTime reviewDeletedTime;
    private BigDecimal reviewStar;
}
