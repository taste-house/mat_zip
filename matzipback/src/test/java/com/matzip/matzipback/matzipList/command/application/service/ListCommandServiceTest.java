package com.matzip.matzipback.matzipList.command.application.service;

import com.matzip.matzipback.exception.ErrorCode;
import com.matzip.matzipback.exception.RestApiException;
import com.matzip.matzipback.matzipList.command.application.dto.CreateListRequest;
import com.matzip.matzipback.matzipList.command.application.dto.DeleteListRequest;
import com.matzip.matzipback.matzipList.command.application.dto.UpdateListRequest;
import com.matzip.matzipback.matzipList.command.domain.repository.ListDomainRepository;
import com.matzip.matzipback.users.command.domain.service.UserActivityDomainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional // 테스트 후 DB에 반영되지 않고 롤백
class ListCommandServiceTest {

    @Autowired
    private ListCommandService listCommandService;

    @Autowired
    private ListDomainRepository listDomainRepository;

    @Autowired
    private UserActivityDomainService userActivityDomainService;

    private CreateListRequest createListRequest;
    private UpdateListRequest updateListRequest;
    private DeleteListRequest deleteListRequest;

    @BeforeEach
    void setUp() {
        createListRequest = new CreateListRequest("리스트 제목", "리스트 설명");
        updateListRequest = new UpdateListRequest(1L, "수정된 제목", "수정된 설명");
        deleteListRequest = new DeleteListRequest(1L);
    }

    @DisplayName("리스트 등록 테스트")
    @Test
    void createList() {
        // when: 리스트 등록
        Long listSeq = listCommandService.createList(createListRequest);

        // then: 생성된 리스트의 seq가 null이 아닌지 확인
        assertNotNull(listSeq, "리스트가 성공적으로 등록되었습니다.");
    }

    @DisplayName("리스트 수정 테스트")
    @Test
    void updateList() {
        // given: 테스트용 리스트 생성
        Long listSeq = listCommandService.createList(createListRequest);

        // when: 리스트 수정
        listCommandService.updateList(new UpdateListRequest(listSeq, "수정된 제목", "수정된 설명"));
    }

    @DisplayName("리스트 삭제 테스트")
    @Test
    void deleteList() {
        // given: 테스트용 리스트 생성
        Long listSeq = listCommandService.createList(createListRequest);

        // when: 리스트 삭제
        listCommandService.deleteList(new DeleteListRequest(listSeq));

        // then: 삭제 후 리스트를 찾을 수 없다는 예외가 발생해야 함
        assertThrows(RestApiException.class, () -> {
            listDomainRepository.findById(listSeq).orElseThrow(() -> new RestApiException(ErrorCode.NOT_FOUND));
        }, "리스트가 이미 삭제된 상태입니다.");
    }
}
