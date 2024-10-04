package com.matzip.matzipuser.like.command.infrastructure.repository;


import com.matzip.matzipuser.like.command.domain.aggregate.Like;
import com.matzip.matzipuser.like.command.domain.repository.ListCmtLikeRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListCmtLikeInfraRepository extends JpaRepository<Like, Long>, ListCmtLikeRepository {

}
