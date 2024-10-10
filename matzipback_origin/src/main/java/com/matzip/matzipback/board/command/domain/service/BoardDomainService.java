package com.matzip.matzipback.board.command.domain.service;

import com.matzip.matzipback.board.command.application.dto.BoardLikeDTO;
import com.matzip.matzipback.board.command.domain.aggregate.FavoriteBoard;
import com.matzip.matzipback.board.command.domain.repository.BoardDomainRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardDomainService {

    private final BoardDomainRepository boardDomainRepository;
    private final ModelMapper modelMapper;

    public boolean existsBoardLike(Long loginUser, Long BoardCategorySeq) {

        return boardDomainRepository
                .existsByUserSeqAndBoardCategorySeq(loginUser, BoardCategorySeq);
    }

    public void save(Long loginUser, Long boardCategorySeq) {
        boardDomainRepository.save(
                modelMapper.map(
                        new BoardLikeDTO(loginUser, boardCategorySeq), FavoriteBoard.class));
    }


    public void deleteBoardLike(Long loginUser, Long boardCategorySeq) {
        boardDomainRepository.deleteByUserSeqAndBoardCategorySeq(loginUser, boardCategorySeq);
    }
}
