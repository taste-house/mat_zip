package com.matzip.matzipback.board.query.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PostCmtListResponseDTO {
    private List<PostCommentDTO> comments;    // 회원 본인이 작성한 댓글 리스트
    private int currentPage;        // 현재 페이지
    private int totalPages;         // 전체 페이지 수
    private long totalPostComments;        // 회원 본인이 작성한 총 댓글 수
}
