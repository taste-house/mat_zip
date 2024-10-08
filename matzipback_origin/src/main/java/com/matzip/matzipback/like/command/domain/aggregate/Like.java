package com.matzip.matzipback.like.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`like`")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeSeq;
    private Long likeUserSeq;
    private Long postSeq;
    private Long postCommentSeq;
    private Long listSeq;
    private Long listCommentSeq;
    private Long reviewSeq;

    private Like(Long likeUserSeq, Long postCommentSeq) {
        this.likeUserSeq = likeUserSeq;
        this.postCommentSeq = postCommentSeq;
    }

    public static Like create(Long likeUserSeq, Long postCommentSeq) {
        return new Like(likeUserSeq, postCommentSeq);
    }
}
