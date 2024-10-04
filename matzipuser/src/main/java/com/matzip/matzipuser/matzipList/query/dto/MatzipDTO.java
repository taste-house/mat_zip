package com.matzip.matzipuser.matzipList.query.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatzipDTO {

    private String restaurantTitle;
    private String listMatzipComment;
    private String restaurantAddress;
    private String restaurantPhone;
    private String restaurantStar;
    private Long likeCount;
}
