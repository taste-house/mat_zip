package com.matzip.matzipuser.board.query.service;

import com.matzip.matzipuser.board.query.dto.PostCmtListResponseDTO;
import com.matzip.matzipuser.board.query.dto.PostCommentDTO;
import com.matzip.matzipuser.board.query.mapper.PostCmtMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostCmtQueryService {

    private final PostCmtMapper postCmtMapper;

    @Transactional
    public PostCmtListResponseDTO getCommentsByUserSeq(Integer page, Integer size, Long userSeq) {

        // offset : 몇번째 row부터 출력할지 설정
        int offset = (page - 1) * size;

        // 회원이 작성한 게시글 댓글 조회
        List<PostCommentDTO> comments = postCmtMapper.getPostCmtsByUserSeq(offset, size, userSeq);


        // 회원이 작성한 총 댓글 갯수
        long totalPostComments = postCmtMapper.countPostCmtsByUserSeq(userSeq);

        return PostCmtListResponseDTO.builder()
                .comments(comments)
                .currentPage(page)
                .totalPages((int) Math.ceil((double)totalPostComments / size))
                .totalPostComments(totalPostComments)
                .build();
    }

}
