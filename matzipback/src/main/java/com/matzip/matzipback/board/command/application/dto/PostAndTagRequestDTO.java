package com.matzip.matzipback.board.command.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostAndTagRequestDTO {
    private Long postSeq;
    private String postTitle;       // 게시글 제목
    private Long postUserSeq;       // 게시물 작성자
    private String postContent;     // 게시글 본문
    private Long boardCategorySeq;  // 게시판 카테고리 고유번호
    private Long listSeq;           // 연결된 리스트 고유번호
    private Long restaurantSeq;     // 연결된 음식점 고유번호
    private List<Long> tagSeq;      // 게시글 태그
}
