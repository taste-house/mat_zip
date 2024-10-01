package com.matzip.matzipback.board.command.domain.aggregate;

import com.matzip.matzipback.board.command.application.dto.ReqPostCmtCreateDTO;
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
        // 생성 전에 실행되서 Status 값 active로 설정
        if(postCommentStatus == null) {
            postCommentStatus = "active";
        }
    }

    // DTO -> Entity (생성자 사용을 안하려고 따로 만든 메서드)
//    public static PostComment create(ReqPostCmtCreateDTO reqPostCmtDTO, Long userSeq) {
//        return new PostComment(
//                reqPostCmtDTO.getPostSeq(),
//                userSeq,
//                reqPostCmtDTO.getPostCommentContent()
//        );
//    }

    // 요청 받은 댓글 내용을 기존 댓글에서 수정
    public PostComment updatePostCmt(String postCommentContent) {
        this.postCommentContent = postCommentContent;
        return this;
    }
}
