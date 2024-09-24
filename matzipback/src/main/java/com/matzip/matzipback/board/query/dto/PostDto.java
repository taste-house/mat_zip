package com.matzip.matzipback.board.query.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class PostDto {
    private String boardCategoryName;   // 게시판 카테고리 이름
    private String postTitle;           // 게시글 제목
    private String userNickname;        // 게시글 작성자 닉네임
    private LocalDateTime postTime;     // 게시글 생성 시간(수정 했다면 마지막 수정 시간)
}
