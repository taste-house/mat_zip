package com.matzip.matzipback.review.command.application.controller;

import com.matzip.matzipback.common.util.CustomUserUtils;
import com.matzip.matzipback.review.command.application.dto.ReviewCreateRequest;
import com.matzip.matzipback.review.command.application.service.ReivewCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Tag(name = "Review", description = "리뷰")
public class ReviewCommandController {

    private final ReivewCommandService reivewCommandService;

    // 리뷰 작성
    @PostMapping(value = "/review", consumes = {"multipart/form-data"})
    @Operation(summary = "리뷰 등록", description = "리뷰를 등록한다.")
    public ResponseEntity<Void> createReview(
            @RequestPart @Valid ReviewCreateRequest reviewRequest,
            @RequestPart(required = false) List<MultipartFile> reviewImages) {

        Long authUserSeq = CustomUserUtils.getCurrentUserSeq();
        Long reviewSeq = reivewCommandService.createReview(authUserSeq, reviewRequest, reviewImages);

        return ResponseEntity
                .created(URI.create("/api/v1/review/" + reviewSeq))
                .build();
    }

}
