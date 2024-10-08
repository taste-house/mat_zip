package com.matzip.matzipback.board.command.domain.repository;

import com.matzip.matzipback.board.command.domain.aggregate.FavoriteBoard;

public interface BoardDomainRepository {

    boolean existsByUserSeqAndBoardCategorySeq(Long userSeq, Long BoardCategorySeq);

    FavoriteBoard save(FavoriteBoard favoriteBoard);

    void deleteByUserSeqAndBoardCategorySeq(Long loginUser, Long boardCategorySeq);
}
