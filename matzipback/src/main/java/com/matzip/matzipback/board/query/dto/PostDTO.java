package com.matzip.matzipback.board.query.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostDTO {
    private String boardCategoryName;           // 게시판 카테고리 이름
    private String postTitle;                   // 게시글 제목
    private String userNickname;                // 게시글 작성자 닉네임
    private LocalDateTime postCreatedTime;      // 게시글 생성 시간
    private LocalDateTime postUpdatedTime;      // 게시글 수정 시간
}
