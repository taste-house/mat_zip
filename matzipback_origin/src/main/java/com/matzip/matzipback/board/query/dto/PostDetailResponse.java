package com.matzip.matzipback.board.query.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class PostDetailResponse {

    private PostDTO post;
    private List<PostTagDTO> tags;
    private ListDTO list;
    private RestaurantDTO restaurant;
    private Long likeCount;
    private List<PostCommentDTO> comments;

}
