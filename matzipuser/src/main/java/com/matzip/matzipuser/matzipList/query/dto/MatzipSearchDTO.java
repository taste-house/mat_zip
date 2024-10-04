package com.matzip.matzipuser.matzipList.query.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatzipSearchDTO {
    private String restaurantTitle;
    private String restaurantAddress;
    private String restaurantPhone;
    private Long restaurantStar;
}
