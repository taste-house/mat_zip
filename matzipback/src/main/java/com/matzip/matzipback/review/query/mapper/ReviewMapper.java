package com.matzip.matzipback.review.query.mapper;

import com.matzip.matzipback.review.query.dto.ReviewDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReviewMapper {

    List<ReviewDto> selectReviews(
            @Param("offset") int offset,
            @Param("limit") int limit,
            @Param("userNickname") String userNickname,
            @Param("reviewContent") String reviewContent,
            @Param("restaurantTitle") String restaurantTitle);

    long countReviews(
            @Param("userNickname") String userNickname,
            @Param("reviewContent") String reviewContent,
            @Param("restaurantTitle") String restaurantTitle);
}
