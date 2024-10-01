package com.matzip.matzipback.review.command.application.service;

import com.matzip.matzipback.common.util.FileUploadUtils;
import com.matzip.matzipback.review.command.application.dto.ReviewCreateRequest;
import com.matzip.matzipback.review.command.application.dto.ReviewImageRequest;
import com.matzip.matzipback.review.command.domain.aggregate.Review;
import com.matzip.matzipback.review.command.domain.aggregate.ReviewImage;
import com.matzip.matzipback.review.command.domain.repository.ReviewImageRepository;
import com.matzip.matzipback.review.command.domain.repository.ReviewRepository;
import com.matzip.matzipback.review.command.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReivewCommandService {

    @Value("${IMAGE_URL}")
    private String IMAGE_URL;
    @Value("${IMAGE_DIR}")
    private String IMAGE_DIR;

    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public Long createReview(Long authUserSeq, ReviewCreateRequest reviewRequest, List<MultipartFile> reviewImages) {

        // 리뷰 저장
        Review newReview = reviewRepository.save(
                ReviewMapper.toEntity(authUserSeq, reviewRequest));

        // 이미지 저장
        for (MultipartFile reviewImage : reviewImages) {
            imageUpload(reviewImage, newReview.getReviewSeq());
        }

        // 음식점 별점 수정

        return newReview.getReviewSeq();
    }

    private void imageUpload(MultipartFile image, Long reviewSeq) {
        reviewImageRepository.save(
                modelMapper.map(ReviewImageRequest.builder()
                        .reviewSeq(reviewSeq)
                        .reviewImagePath(FileUploadUtils.saveFile(IMAGE_DIR, image)),
                        ReviewImage.class));
    }
}
