package com.matzip.matzipback.board.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "post")
@EntityListeners(AuditingEntityListener.class)  // Auditing 활성화 -> CreateDate, LastModifiedDate 작동
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE post SET post_status = 'delete', post_deleted_time = LOCALTIME WHERE post_seq = ?")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postSeq;
    private Long postUserSeq;
    private Long boardCategorySeq;
    private Long restaurantSeq;
    private Long listSeq;
    private String postTitle;
    private String postContent;
    private String postStatus;
    @CreatedDate
    private LocalDateTime postCreatedTime;
    @LastModifiedDate
    private LocalDateTime postUpdatedTime;
    private LocalDateTime postDeletedTime;

    @PrePersist
    public void prePersist() {
        // 생성 전에 실행되서 Status 값 active로 설정
        if(postStatus == null) {
            postStatus = "active";
        }
        postUpdatedTime = null; // 명시적으로 null 설정
    }

}
