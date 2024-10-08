package com.matzip.matzipback.review.query.mapper;

import com.matzip.matzipback.review.query.dto.ReviewDetailResponse;
import com.matzip.matzipback.review.query.dto.ReviewListDto;
import com.matzip.matzipback.review.query.dto.ReviewByRestaurantDTO;
import com.matzip.matzipback.review.query.dto.ReviewImageDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface ReviewMapper {

    List<ReviewListDto> selectReviews(
            @Param("offset") int offset,
            @Param("limit") int limit,
            @Param("userNickname") String userNickname,
            @Param("reviewContent") String reviewContent,
            @Param("restaurantTitle") String restaurantTitle);

    long countReviews(
            @Param("userNickname") String userNickname,
            @Param("reviewContent") String reviewContent,
            @Param("restaurantTitle") String restaurantTitle);

    ReviewDetailResponse selectReview(
            @Param("reviewSeq") Long reviewSeq);

    List<ReviewImageDto> selectReviewImages(
            @Param("reviewSeq") Long reviewSeq);

    BigDecimal selectRestaurantStarAverage(
            @Param("restaurantSeq") Long restaurantSeq);

    List<ReviewByRestaurantDTO> selectReviewsByRestaurant(
            @Param("offset") int offset,
            @Param("limit") int limit,
            @Param("restaurantSeq") Long restaurantSeq);

    long countReviewsByRestaurant(
            @Param("restaurantSeq") Long restaurantSeq);
}
