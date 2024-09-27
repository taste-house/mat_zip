package com.matzip.matzipback.like.query.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class LikedPostResMessageDTO {

    private int code;
    private String message;
    private List<LikedPostDTO> LikedPosts;
}
