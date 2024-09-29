package com.matzip.matzipback.matzipList.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "list_comment")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Getter
@SQLDelete(sql = "UPDATE list_comment SET list_comment_status = 'delete', list_comment_deleted_time = NOW() WHERE list_comment_seq = ?")
public class MyListComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long listCommentSeq;
    private Long listSeq;
    private Long listCommentUserSeq;
    private String listCommentContent;
    private String listCommentStatus;
    @CreatedDate
    private LocalDateTime listCommentCreatedTime;
    @LastModifiedDate
    private LocalDateTime listCommentUpdatedTime;
    private LocalDateTime listCommentDeletedTime;

    @PrePersist
    public void prePersist() {
        if (listCommentStatus == null) listCommentStatus = "active";
    }

    public MyListComment(Long listSeq, Long listCommentUserSeq, String listCommentContent) {
        this.listSeq = listSeq;
        this.listCommentUserSeq = listCommentUserSeq;
        this.listCommentContent = listCommentContent;
    }

    public static MyListComment create(Long listSeq, Long listCommentUserSeq, String listCommentContent) {
        return new MyListComment(listSeq, listCommentUserSeq, listCommentContent);
    }
}



