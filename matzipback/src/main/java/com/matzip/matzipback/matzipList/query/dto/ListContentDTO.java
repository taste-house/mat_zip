package com.matzip.matzipback.matzipList.query.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ListContentDTO {

    private String listTitle;
    private String listContent;
    private String restaurantTitle;
    private String listMatzipComment;
    private String restaurantAddress;
    private String restaurantPhone;
    private String restaurantStar;
    private LocalDateTime listCreatedTime;
    private LocalDateTime listUpdatedTime;
    private Long likeCount;
}
