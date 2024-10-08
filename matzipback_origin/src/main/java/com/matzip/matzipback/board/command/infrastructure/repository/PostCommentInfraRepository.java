package com.matzip.matzipback.board.command.infrastructure.repository;

import com.matzip.matzipback.board.command.domain.aggregate.PostComment;
import com.matzip.matzipback.board.command.domain.repository.PostCommentRepository;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostCommentInfraRepository extends JpaRepository<PostComment, Long>, PostCommentRepository {
    // 가장 최근에 생성된 댓글을 가져오는 메서드
    PostComment findTopByOrderByPostCommentSeqDesc();

    // 소프트 삭제된 항목 조회
    @Query("SELECT p FROM PostComment p WHERE p.postCommentSeq = :postCommentSeq")
    PostComment findByIdIncludingDeleted(@Param("postCommentSeq") Long postCommentSeq);
}
