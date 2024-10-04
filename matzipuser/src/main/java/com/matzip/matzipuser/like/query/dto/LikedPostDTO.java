package com.matzip.matzipuser.like.query.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LikedPostDTO {

    private String postTitle; // 게시글 제목
    private String userNickname; // 유저 닉네임
    private LocalDateTime postUpdatedTime; // 게시글 수정 시간
}
