package com.matzip.matzipback.review.command.domain.aggregate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "review")
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE review SET review_status = 'delete', review_deleted_time = NOW() WHERE review_seq = ?")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewSeq;
    private Long reviewUserSeq;
    private Long restaurantSeq;
    private String reviewContent;
    private String reviewStatus;
    @CreatedDate
    private LocalDateTime reviewCreatedTime;
    @LastModifiedDate
    private LocalDateTime reviewUpdatedTime;
    private LocalDateTime reviewDeletedTime;
    private BigDecimal reviewStar;

    private Review(Long authUserSeq, Long restaurantSeq, String reviewContent, BigDecimal reviewStar) {
        this.reviewUserSeq = authUserSeq;
        this.restaurantSeq = restaurantSeq;
        this.reviewContent = reviewContent;
        this.reviewStar = reviewStar;
        this.reviewStatus = "active";
    }

    public static Review create(Long authUserSeq, Long restaurantSeq, String reviewContent, BigDecimal reviewStar) {
        return new Review(authUserSeq, restaurantSeq, reviewContent, reviewStar);
    }
}
