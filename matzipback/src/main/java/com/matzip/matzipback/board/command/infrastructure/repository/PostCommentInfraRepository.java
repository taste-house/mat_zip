package com.matzip.matzipback.board.command.infrastructure.repository;

import com.matzip.matzipback.board.command.domain.aggregate.PostComment;
import com.matzip.matzipback.board.command.domain.repository.PostCommentRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentInfraRepository extends JpaRepository<PostComment, Long>, PostCommentRepository {

}
