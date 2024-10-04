package com.matzip.matzipuser.board.command.domain.aggregate;

import com.matzip.matzipuser.board.command.application.dto.ReqPostCmtUpdateDTO;
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
@Table(name = "post_comment")
@EntityListeners(AuditingEntityListener.class)  // Auditing 활성화 -> CreateDate, LastModifiedDate 작동
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
    @Column(updatable = false)  // 업데이트 시에도 변경되지 않는 설정
    private LocalDateTime postCommentCreatedTime;
    @LastModifiedDate
    private LocalDateTime postCommentUpdatedTime;
    private LocalDateTime postCommentDeletedTime;


    private PostComment(Long postSeq, Long postCommentUserSeq
            , String postCommentContent) {
        this.postSeq = postSeq;
        this.postCommentUserSeq = postCommentUserSeq;
        this.postCommentContent = postCommentContent;
    }

    @PrePersist
    public void prePersist() {
        // 생성 전에 실행되서 Status 값 active 로 설정
        if(postCommentStatus == null) {
            postCommentStatus = "active";
        }

        postCommentUpdatedTime = null;
    }

    // 요청 받은 댓글 내용을 기존 댓글에서 수정
    public void updatePostCmt(ReqPostCmtUpdateDTO reqPostCmtUpdateDTO) {
        this.postCommentContent = reqPostCmtUpdateDTO.getPostCommentContent();
    }

    public void updatepostCmtStatus(String status) {
        this.postCommentStatus = status;
    }
}
