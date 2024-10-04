package com.matzip.matzipuser.restaurant.command.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RestaurantUpdateRequest {

    @NotBlank
    private final String restaurantTitle;
    @NotBlank
    private final String restaurantAddress;
    private final String restaurantPhone;
}
