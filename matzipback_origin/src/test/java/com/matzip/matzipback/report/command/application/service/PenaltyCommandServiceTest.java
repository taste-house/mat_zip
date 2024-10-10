package com.matzip.matzipback.report.command.application.service;


import com.matzip.matzipback.report.command.dto.PenaltyUpdateRequest;
import com.matzip.matzipback.report.command.dto.ReportAndPenaltyDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PenaltyCommandServiceTest {

    @Autowired
    private PenaltyCommandService penaltyCommandService;

    // 패널티 등록 테스트 데이터 제공 메소드
    private static Stream<Arguments> newPenalty() {
        return Stream.of(
                Arguments.of(new ReportAndPenaltyDTO(
                        "post",
                        3L,
                        3L,
                        LocalDateTime.now(),  // 시작일
                        LocalDateTime.of(2024, 10, 10, 12, 0, 0), // 종료일
                        "ban",
                        "욕설"
                ))
        );
    }

    // 패널티 수정 테스트 데이터 제공 메소드
    private static Stream<Arguments> updatePenalty() {
        return Stream.of(
                Arguments.of(14L, new PenaltyUpdateRequest(
                        LocalDateTime.now(),  // 시작일
                        LocalDateTime.of(2024, 10, 10, 12, 0, 0), // 종료일
                        "permanent",
                        "비방"
                ))
        );
    }

    @DisplayName("패널티 등록 테스트")
    @ParameterizedTest
    @MethodSource("newPenalty")
    void createPenalty(ReportAndPenaltyDTO newPenalty) {
        // when: PostCommandService를 사용하여 패널티 등록
        Long penaltySeq = penaltyCommandService.createPenalty(newPenalty);

        // then: 생성된 게시글의 seq가 null이 아닌지 확인
        System.out.println(penaltySeq);
        assertNotNull(penaltySeq, "패널티가 성공적으로 등록되었습니다.");
    }

    @DisplayName("패널티 수정 테스트")
    @ParameterizedTest
    @MethodSource("updatePenalty")
    void updatePenalty(Long penaltySeq, PenaltyUpdateRequest updatePenalty) {
        // when: PostCommandService를 사용하여 게시글 수정
        penaltyCommandService.updatePenalty(penaltySeq, updatePenalty);

        // then: 성공적으로 수정되었는지 확인
        assertDoesNotThrow(() -> penaltyCommandService.updatePenalty(penaltySeq, updatePenalty), "게시글이 성공적으로 수정되었습니다.");

    }
}