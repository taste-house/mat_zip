package com.matzip.matzipback.restaurant.query.controller;

import com.matzip.matzipback.restaurant.query.dto.RestaurantDetailResponse;
import com.matzip.matzipback.restaurant.query.dto.RestaurantListResponse;
import com.matzip.matzipback.restaurant.query.service.RestaurantQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/back/api/v1")
@Tag(name = "Restaurant", description = "음식점")
public class RestaurantQueryController {

    private final RestaurantQueryService restaurantQueryService;

    @GetMapping("/restaurant")
    @Operation(summary = "음식점 검색", description = "음식점을 검색한다.")
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
    @Operation(summary = "음식점 상세 조회", description = "개별 음식점을 조회한다.")
    public ResponseEntity<RestaurantDetailResponse> getRestaurants(@PathVariable Long restaurantSeq) {

        RestaurantDetailResponse response = restaurantQueryService.getRestaurant(restaurantSeq);

        return ResponseEntity.ok(response);
    }
}
