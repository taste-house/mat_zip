package com.matzip.matzipuser.restaurant.query.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter @Builder
public class RestaurantListResponse {
    private List<RestaurantDto> restaurants;
    private int currentPage;
    private int totalPages;
    private long totalItems;
}
