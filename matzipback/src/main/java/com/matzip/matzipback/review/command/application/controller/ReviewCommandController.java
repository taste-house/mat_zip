package com.matzip.matzipback.review.command.application.controller;

import com.matzip.matzipback.common.util.CustomUserUtils;
import com.matzip.matzipback.responsemessage.SuccessCode;
import com.matzip.matzipback.responsemessage.SuccessResMessage;
import com.matzip.matzipback.review.command.application.dto.ReviewCreateRequest;
import com.matzip.matzipback.review.command.application.dto.ReviewUpdateRequest;
import com.matzip.matzipback.review.command.application.service.ReviewCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/back/api/v1")
@Tag(name = "Review", description = "리뷰")
public class ReviewCommandController {

    private final ReviewCommandService reviewCommandService;

    @PostMapping(value = "/review", consumes = {"multipart/form-data"})
    @Operation(summary = "리뷰 등록", description = "리뷰를 등록한다.")
    public ResponseEntity<Void> createReview(
            @RequestPart @Valid ReviewCreateRequest reviewRequest,
            @RequestPart(required = false) List<MultipartFile> reviewImages) {

        Long authUserSeq = CustomUserUtils.getCurrentUserSeq();
        Long reviewSeq = reviewCommandService.createReview(authUserSeq, reviewRequest, reviewImages);

        return ResponseEntity
                .created(URI.create("/back/api/v1/review/" + reviewSeq))
                .build();
    }

    @PutMapping(value = "/review/{reviewSeq}")
    @Operation(summary = "리뷰 수정", description = "리뷰를 수정한다.")
    public ResponseEntity<SuccessResMessage> updateReview(
            @PathVariable Long reviewSeq,
            @RequestBody @Valid ReviewUpdateRequest reviewRequest) {

        reviewCommandService.updateReview(reviewSeq, reviewRequest);

        return ResponseEntity.ok(new SuccessResMessage(SuccessCode.BASIC_UPDATE_SUCCESS));
    }

    @DeleteMapping(value = "/review/{reviewSeq}")
    @Operation(summary = "리뷰 삭제", description = "리뷰를 삭제한다.")
    public ResponseEntity<SuccessResMessage> deleteReview(
            @PathVariable Long reviewSeq) {

        reviewCommandService.deleteReview(reviewSeq);

        return ResponseEntity.ok(new SuccessResMessage(SuccessCode.BASIC_DELETE_SUCCESS));
    }
}
