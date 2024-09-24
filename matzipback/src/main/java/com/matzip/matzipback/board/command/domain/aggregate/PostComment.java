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
@Table(name = "post_comment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE post_comment SET post_comment_status = 'delete', post_comment_deleted_time = LOCALTIME WHERE post_comment_seq = ?")
public class PostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postCommentSeq;
    private Long postSeq;
    private Long postCommentUserSeq;
    private String postCommentContent;
    private String postCommentStatus;
    @CreatedDate
    private LocalDateTime postCommentCreatedTime;
    @LastModifiedDate
    private LocalDateTime postCommentUpdatedTime;
    private LocalDateTime postCommentDeletedTime;

}
