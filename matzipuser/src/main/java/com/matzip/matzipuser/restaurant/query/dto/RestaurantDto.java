package com.matzip.matzipuser.restaurant.query.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RestaurantDto {
    private Long restaurantSeq;
    private String restaurantTitle;
    private String restaurantAddress;
    private String restaurantPhone;
    private String restaurantStar;
}
