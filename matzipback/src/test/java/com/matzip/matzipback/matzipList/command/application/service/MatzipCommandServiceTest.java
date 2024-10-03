package com.matzip.matzipback.matzipList.command.application.service;

import com.matzip.matzipback.matzipList.command.application.dto.CreateMatzipRequest;
import com.matzip.matzipback.matzipList.command.application.dto.DeleteMatzipRequest;
import com.matzip.matzipback.matzipList.command.application.dto.UpdateMatzipRequest;
import com.matzip.matzipback.matzipList.command.domain.aggregate.MyListMatzip;
import com.matzip.matzipback.matzipList.command.domain.repository.MatzipDomainRepository;
import com.matzip.matzipback.matzipList.command.domain.service.DomainMatzipService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MatzipCommandServiceTest {

    @InjectMocks
    private MatzipCommandService matzipCommandService;

    @Mock
    private MatzipDomainRepository matzipDomainRepository;

    @Mock
    private DomainMatzipService domainMatzipService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateMatzip() {
        // Given
        CreateMatzipRequest request = new CreateMatzipRequest(1L, 2L, "맛집 코멘트");
        MyListMatzip newMatzip = MyListMatzip.create(1L, 2L, "맛집 코멘트");
        when(matzipDomainRepository.save(any(MyListMatzip.class))).thenReturn(newMatzip);

        // When
        Long result = matzipCommandService.createMatzip(request);

        // Then
        assertEquals(newMatzip.getListMatzipSeq(), result);
        verify(matzipDomainRepository, times(1)).save(any(MyListMatzip.class));
    }

    @Test
    void testDeleteMatzip() {
        // Given
        DeleteMatzipRequest request = new DeleteMatzipRequest();
        request.setListMatzipSeq(1L);

        // When
        matzipCommandService.deleteMatzip(request);

        // Then
        verify(matzipDomainRepository, times(1)).deleteById(1L);
    }

    @Test
    void testUpdateMatzip() {
        // Given
        UpdateMatzipRequest request = new UpdateMatzipRequest(1L, 2L, 3L, "수정된 맛집 코멘트");
        when(domainMatzipService.updateMatzip(request)).thenReturn(2L);

        // When
        Long result = matzipCommandService.updateMatzip(request);

        // Then
        assertEquals(2L, result);
        verify(domainMatzipService, times(1)).updateMatzip(request);
    }
}
