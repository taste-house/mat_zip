package com.matzip.matzipback.board.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE post SET post_status = 'delete', post_deleted_time = LOCALTIME WHERE post_seq = ?")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postSeq;
    private Long postUserSeq;
    private Long listSeq;
    private Long boardCategorySeq;
    private String postTitle;
    private String postContent;
    private String postStatus;
    @CreatedDate
    private LocalDateTime postCreatedTime;
    @LastModifiedDate
    private LocalDateTime postUpdatedTime;
    private LocalDateTime postDeletedTime;

}
