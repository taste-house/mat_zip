package com.matzip.matzipback.report.command.application.controller;

import com.matzip.matzipback.common.util.CustomUserUtils;
import com.matzip.matzipback.exception.RestApiException;
import com.matzip.matzipback.report.command.application.service.PenaltyCommandService;
import com.matzip.matzipback.report.command.dto.PenaltyUpdateRequest;
import com.matzip.matzipback.report.command.dto.ReportAndPenaltyDTO;
import com.matzip.matzipback.responsemessage.SuccessCode;
import com.matzip.matzipback.responsemessage.SuccessResMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static com.matzip.matzipback.exception.ErrorCode.FORBIDDEN_ACCESS;
import static com.matzip.matzipback.exception.ErrorCode.UNAUTHORIZED_REQUEST;

@RestController
@RequiredArgsConstructor
@RequestMapping("/back/api/v1")
@Tag(name = "Penalty", description = "패널티")
public class PenaltyCommandController {

    private final PenaltyCommandService penaltyCommandService;

    /* 1. 패널티 등록 */
    @PostMapping("/penalty")
    @Operation(summary = "패널티 등록", description = "관리자가 신고 받은 사용자에 대한 패널티를 등록한다.")
    public ResponseEntity<Void> registPenalty(@Valid @RequestBody ReportAndPenaltyDTO newPenalty) {

        // 관리자 여부 확인
        try {
            if (CustomUserUtils.getCurrentUserAuthorities().iterator().next().getAuthority().equals("admin")) {

                // 패널티 등록
                Long penaltySeq = penaltyCommandService.createPenalty(newPenalty);

                return ResponseEntity.status(HttpStatus.CREATED)
                        .location(URI.create("/back/api/v1/penalty/" + penaltySeq))
                        .build();

            } else {
                throw new RestApiException(FORBIDDEN_ACCESS);   // 권한 없음
            }
        } catch (NullPointerException e) {
            throw new RestApiException(UNAUTHORIZED_REQUEST);   // 로그인, 인증 안 한 사람
        }
    }

    /* 2. 패널티 수정 */
    @PutMapping("/penalty/{penaltySeq}")
    @Operation(summary = "패널티 수정", description = "관리자가 패널티를 수정한다.")
    public ResponseEntity<SuccessResMessage> updatePenalty(
            @PathVariable Long penaltySeq, @RequestBody PenaltyUpdateRequest updatePenalty) {

        // 관리자 여부 확인
        try {
            if (CustomUserUtils.getCurrentUserAuthorities().iterator().next().getAuthority().equals("admin")) {

                // 패널티 내용 수정
                penaltyCommandService.updatePenalty(penaltySeq, updatePenalty);

                return ResponseEntity.status(HttpStatus.CREATED)
                        .location(URI.create("/back/api/v1/penalty/" + penaltySeq))
                        .build();

            } else {
                throw new RestApiException(FORBIDDEN_ACCESS);   // 권한 없음
            }
        } catch (NullPointerException e) {
            throw new RestApiException(UNAUTHORIZED_REQUEST);   // 로그인, 인증 안 한 사람
        }
    }

    /* 3. 패널티 철회 */
    @DeleteMapping("/penalty/{penaltySeq}")
    @Operation(summary = "패널티 철회", description = "관리자가 패널티를 철회한다.")
    public ResponseEntity<SuccessResMessage> deletePenalty(@PathVariable Long penaltySeq) {

        // 관리자 권한 확인
        try {
            if (CustomUserUtils.getCurrentUserAuthorities().iterator().next().getAuthority().equals("admin")) {
                // 패널티 철회
                penaltyCommandService.deletePenalty(penaltySeq);

                return ResponseEntity.ok(new SuccessResMessage(SuccessCode.BASIC_DELETE_SUCCESS));
            } else {
                throw new RestApiException(FORBIDDEN_ACCESS);   // 권한 없음
            }
        } catch (NullPointerException e) {
            throw new RestApiException(UNAUTHORIZED_REQUEST);   // 로그인, 인증 안 한 사람
        }
    }

}
