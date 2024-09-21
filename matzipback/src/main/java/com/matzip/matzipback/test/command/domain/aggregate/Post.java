package com.matzip.matzipback.test.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "post")
@NoArgsConstructor
@SQLDelete(sql = "UPDATE post SET post_status = 'delete', post_deleted_time = LOCALTIME WHERE post_seq = ?")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postSeq;
    private int postUserSeq;
    private int listSeq;
    private int boardCategorySeq;
    private String postTitle;
    private String postContent;
    private String postStatus;
    @CreatedDate
    private LocalDateTime postCreatedTime;
    @LastModifiedDate
    private LocalDateTime postUpdatedTime;
    private LocalDateTime postDeletedTime;

}
