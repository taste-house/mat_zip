package com.matzip.matzipback.board.command.domain.aggregate;

import com.matzip.matzipback.board.command.application.dto.ReqPostCmtCreateDTO;
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
    private String postCommentStatus = "active";
    @CreatedDate
    private LocalDateTime postCommentCreatedTime;
    @LastModifiedDate
    private LocalDateTime postCommentUpdatedTime;
    private LocalDateTime postCommentDeletedTime;


    private PostComment(Long postSeq, Long postCommentUserSeq
            , String postCommentContent, String postCommentStatus) {
        this.postSeq = postSeq;
        this.postCommentUserSeq = postCommentUserSeq;
        this.postCommentContent = postCommentContent;
        this.postCommentStatus = postCommentStatus;
    }

    // DTO -> Entity (생성자 사용을 안하려고 따로 만든 메서드)
    public static PostComment create(ReqPostCmtCreateDTO reqPostCmtDTO, Long userSeq) {
        return new PostComment(
                reqPostCmtDTO.getPostSeq(),
                userSeq,
                reqPostCmtDTO.getPostCommentContent(),
                "active"
        );
    }

    // 요청 받은 댓글 내용을 기존 댓글에서 수정
    public void updatePostCmt(String postCommentContent) {
        this.postCommentContent = postCommentContent;
    }
}
