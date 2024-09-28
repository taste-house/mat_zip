package com.matzip.matzipback.restaurant.query.service;

import com.matzip.matzipback.restaurant.query.dto.RestaurantDetailResponse;
import com.matzip.matzipback.restaurant.query.dto.RestaurantDto;
import com.matzip.matzipback.restaurant.query.dto.RestaurantListResponse;
import com.matzip.matzipback.restaurant.query.mapper.RestaurantMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantQueryService {

    private final RestaurantMapper restaurantMapper;

    @Transactional(readOnly = true)
    public RestaurantListResponse getRestaurants(Integer page, Integer size, String restaurantTitle, String restaurantAddress, String restaurantPhone) {

        int offset = (page - 1) * size;
        List<RestaurantDto> restaurants = restaurantMapper.selectRestaurants(offset, size, restaurantTitle, restaurantAddress, restaurantPhone);

        long totalItems = restaurantMapper.countRestaurant(restaurantTitle, restaurantAddress, restaurantPhone);

        return RestaurantListResponse.builder()
                .restaurants(restaurants)
                .currentPage(page)
                .totalPages((int) Math.ceil((double) totalItems / size))
                .totalItems(totalItems)
                .build();
    }

    @Transactional(readOnly = true)
    public RestaurantDetailResponse getRestaurant(Long restaurantSeq) {
        RestaurantDto restaurant = restaurantMapper.selectRestaurantBySeq(restaurantSeq);

        if (restaurant == null) {
            throw new RuntimeException("해당 코드를 가진 음식점을 찾지 못했습니다. 코드: " + restaurantSeq);
        }

        return new RestaurantDetailResponse(restaurant);
    }
}
