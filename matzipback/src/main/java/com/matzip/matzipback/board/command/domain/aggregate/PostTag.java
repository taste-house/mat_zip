package com.matzip.matzipback.board.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "post_tag")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postTagSeq;
    private Long tagSeq;
    private Long postSeq;

    // 생성자 추가
    public PostTag(Long tagSeq, Long postSeq) {
        this.tagSeq = tagSeq;
        this.postSeq = postSeq;
    }
}
