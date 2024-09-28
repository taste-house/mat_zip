package com.matzip.matzipback.restaurant.query.controller;

import com.matzip.matzipback.restaurant.query.dto.RestaurantDetailResponse;
import com.matzip.matzipback.restaurant.query.dto.RestaurantListResponse;
import com.matzip.matzipback.restaurant.query.service.RestaurantQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class RestaurantQueryController {

    private final RestaurantQueryService restaurantQueryService;

    // 음식점 검색
    @GetMapping("/restaurant")
    public ResponseEntity<RestaurantListResponse> getRestaurants(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String phone) {

        RestaurantListResponse response = restaurantQueryService.getRestaurants(page, size, title, address, phone);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/restaurant/{restaurantSeq}")
    public ResponseEntity<RestaurantDetailResponse> getRestaurants(@PathVariable Long restaurantSeq) {

        RestaurantDetailResponse response = restaurantQueryService.getRestaurant(restaurantSeq);

        return ResponseEntity.ok(response);
    }
}