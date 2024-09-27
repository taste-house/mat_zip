package com.matzip.matzipback.like.query.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LikedPostDTO {

    private String postTitle;
    private String userNickname;
    private LocalDateTime postUpdatedTime;
}
