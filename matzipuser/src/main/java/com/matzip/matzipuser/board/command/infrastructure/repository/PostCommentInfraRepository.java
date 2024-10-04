package com.matzip.matzipuser.board.command.infrastructure.repository;

import com.matzip.matzipuser.board.command.domain.aggregate.PostComment;
import com.matzip.matzipuser.board.command.domain.repository.PostCommentRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentInfraRepository extends JpaRepository<PostComment, Long>, PostCommentRepository {

}
