package com.matzip.matzipuser.board.command.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PostAndTagRequestDTO {

    @NotBlank
    private String postTitle;       // 게시글 제목
    @NotBlank
    private String postContent;     // 게시글 본문
    @NotNull
    private Long boardCategorySeq;  // 게시판 카테고리 고유번호
    private Long listSeq;           // 연결된 리스트 고유번호
    private Long restaurantSeq;     // 연결된 음식점 고유번호
    private List<String> tagName;   // 게시글 태그
}
