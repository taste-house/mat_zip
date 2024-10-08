package com.matzip.matzipback.board.command.infrastructure.repository;

import com.matzip.matzipback.board.command.domain.aggregate.FavoriteBoard;
import com.matzip.matzipback.board.command.domain.repository.BoardDomainRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardInfraRepository extends JpaRepository<FavoriteBoard, Long>, BoardDomainRepository {
}
