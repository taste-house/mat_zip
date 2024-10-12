package com.matzip.matzipback.matzipList.query.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListCommentSearchDTO {

    private String userNickname;
    private String listCommentContent;
    private String listCommentUpdatedTime;
    private Long likeCount;
}
