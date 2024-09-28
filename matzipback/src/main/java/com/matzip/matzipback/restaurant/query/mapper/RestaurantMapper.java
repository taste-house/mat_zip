package com.matzip.matzipback.restaurant.query.mapper;

import com.matzip.matzipback.restaurant.query.dto.RestaurantDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RestaurantMapper {
    // 음식점 검색
    List<RestaurantDto> selectRestaurants(
            @Param("offset") int offset,
            @Param("limit") int limit,
            @Param("restaurantTitle") String restaurantTitle,
            @Param("restaurantAddress") String restaurantAddress,
            @Param("restaurantPhone") String restaurantPhone);

    // 페이징을 위한 음식점 검색 결과 갯수
    long countRestaurant(
            @Param("restaurantTitle") String restaurantTitle,
            @Param("restaurantAddress") String restaurantAddress,
            @Param("restaurantPhone") String restaurantPhone);

    // 단일 음식점 조회
    RestaurantDto selectRestaurantBySeq(Long restaurantSeq);
}
