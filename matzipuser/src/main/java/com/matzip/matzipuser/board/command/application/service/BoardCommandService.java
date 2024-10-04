package com.matzip.matzipuser.board.command.application.service;

import com.matzip.matzipuser.board.command.domain.service.BoardDomainService;
import com.matzip.matzipuser.common.util.CustomUserUtils;
import com.matzip.matzipuser.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.matzip.matzipuser.exception.ErrorCode.UNAUTHORIZED_REQUEST;

@Service
@RequiredArgsConstructor
public class BoardCommandService {

    private final BoardDomainService boardDomainService;

    // 즐겨찾기 등록 또는 취소
    @Transactional
    public boolean saveBoardLike(Long boardCategorySeq) {

        Long loginUser = CustomUserUtils.getCurrentUserSeq();

        boolean result = boardDomainService.existsBoardLike(loginUser, boardCategorySeq);

        // 즐겨찾기가 안되어있으면
        if (!result) {
            boardDomainService.save(loginUser, boardCategorySeq);
            return true;
        }

        // 즐겨찾기 되어 있으면 즐겨찾기 취소
        boardDomainService.deleteBoardLike(loginUser, boardCategorySeq);
        return false;
    }

    @Transactional
    public void deleteBoardLike(Long boardCategorySeq) {

        Long loginUser = CustomUserUtils.getCurrentUserSeq();

        boardDomainService.deleteBoardLike(loginUser, boardCategorySeq);
    }
}
