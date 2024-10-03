package com.matzip.matzipback.review.command.application.service;

import com.matzip.matzipback.common.util.CustomUserUtils;
import com.matzip.matzipback.common.util.FileUploadUtils;
import com.matzip.matzipback.exception.RestApiException;
import com.matzip.matzipback.restaurant.command.application.service.RestaurantCommandService;
import com.matzip.matzipback.review.command.application.dto.ReviewCreateRequest;
import com.matzip.matzipback.review.command.application.dto.ReviewImageRequest;
import com.matzip.matzipback.review.command.application.dto.ReviewUpdateRequest;
import com.matzip.matzipback.review.command.domain.aggregate.Review;
import com.matzip.matzipback.review.command.domain.aggregate.ReviewImage;
import com.matzip.matzipback.review.command.domain.repository.ReviewImageRepository;
import com.matzip.matzipback.review.command.domain.repository.ReviewRepository;
import com.matzip.matzipback.review.command.mapper.ReviewMapper;
import com.matzip.matzipback.review.query.service.ReviewQueryService;
import com.matzip.matzipback.users.command.domain.service.UserActivityDomainService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.matzip.matzipback.exception.ErrorCode.FORBIDDEN_ACCESS;
import static com.matzip.matzipback.exception.ErrorCode.UNPROCESSABLE_ENTITY;

@Service
@RequiredArgsConstructor
public class ReviewCommandService {

    @Value("${IMAGE_URL}")
    private String IMAGE_URL;
    @Value("${IMAGE_DIR}")
    private String IMAGE_DIR;

    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final ModelMapper modelMapper;
    private final UserActivityDomainService userActivityDomainService;
    private final ReviewQueryService reviewQueryService;
    private final RestaurantCommandService restaurantCommandService;

    @Transactional
    public Long createReview(Long authUserSeq, ReviewCreateRequest reviewRequest, List<MultipartFile> reviewImages) {

        // 리뷰 저장
        Review newReview = reviewRepository.save(
                ReviewMapper.toEntity(authUserSeq, reviewRequest));

        // 이미지 저장
        if (reviewImages != null) {
            for (MultipartFile reviewImage : reviewImages) {
                imageUpload(reviewImage, newReview.getReviewSeq());
            }
        }

        // 음식점 별점 수정
        restaurantCommandService.updateRestaurantStar(
                newReview.getRestaurantSeq(),
                reviewQueryService.getRestaurantStarAverage(newReview.getRestaurantSeq()));

        // 회원 포인트 변경
        userActivityDomainService.updateUserActivityPoint(authUserSeq, 4);

        return newReview.getReviewSeq();
    }

    @Transactional
    public void imageUpload(MultipartFile image, Long reviewSeq) {
        reviewImageRepository.save(
                modelMapper.map(ReviewImageRequest.builder()
                                .reviewSeq(reviewSeq)
                                .reviewImagePath(IMAGE_DIR + "/" + FileUploadUtils.saveFile(IMAGE_DIR, image)),
                        ReviewImage.class));
    }

    @Transactional
    public Long updateReview(Long reviewSeq, ReviewUpdateRequest reviewRequest) {

        // 원본 리뷰 가져오기
        Review review = reviewRepository.findById(reviewSeq)
                .orElseThrow(() -> new RestApiException(UNPROCESSABLE_ENTITY));

        // 수정 권한 검증
        if (CustomUserUtils.getCurrentUserAuthorities().iterator().next().getAuthority().equals("user")) {
            if (!CustomUserUtils.getCurrentUserSeq().equals(review.getReviewUserSeq())) {
                throw new RestApiException(FORBIDDEN_ACCESS);
            }
        }

        // 원본 리뷰에 수정된 내용 덮어쓰기
        review.updateReviewDetails(
                reviewRequest.getReviewContent(),
                reviewRequest.getReviewStar()
        );

        // 음식점 별점 수정
        restaurantCommandService.updateRestaurantStar(
                review.getRestaurantSeq(),
                reviewQueryService.getRestaurantStarAverage(review.getRestaurantSeq()));

        return review.getReviewSeq();
    }

    @Transactional
    public void deleteReview(Long reviewSeq) {

        // 원본 리뷰 가져오기
        Review review = reviewRepository.findById(reviewSeq)
                .orElseThrow(() -> new RestApiException(UNPROCESSABLE_ENTITY));

        // 삭제 권한 검증
        if (CustomUserUtils.getCurrentUserAuthorities().iterator().next().getAuthority().equals("user")) {
            if (!CustomUserUtils.getCurrentUserSeq().equals(review.getReviewUserSeq())) {
                throw new RestApiException(FORBIDDEN_ACCESS);
            }
        }

        // 리뷰 삭제
        reviewRepository.deleteById(reviewSeq);

        // 음식점 별점 수정
        restaurantCommandService.updateRestaurantStar(
                review.getRestaurantSeq(),
                reviewQueryService.getRestaurantStarAverage(review.getRestaurantSeq()));
    }
}
