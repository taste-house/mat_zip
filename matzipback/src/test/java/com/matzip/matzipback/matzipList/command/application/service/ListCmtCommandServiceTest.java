package com.matzip.matzipback.matzipList.command.application.service;

import com.matzip.matzipback.matzipList.command.application.dto.CreateListCmtRequest;
import com.matzip.matzipback.matzipList.command.application.dto.DeleteListCmtRequset;
import com.matzip.matzipback.matzipList.command.application.dto.UpdateListCmtRequest;
import com.matzip.matzipback.matzipList.command.domain.aggregate.MyListComment;
import com.matzip.matzipback.matzipList.command.domain.repository.ListCmtDomainRepository;
import com.matzip.matzipback.matzipList.command.domain.service.DomainListCmtUpdateService;
import com.matzip.matzipback.users.command.domain.service.UserActivityDomainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListCmtCommandServiceTest {

    @InjectMocks
    private ListCmtCommandService listCmtCommandService;

    @Mock
    private ListCmtDomainRepository listCmtDomainRepository;

    @Mock
    private DomainListCmtUpdateService domainListCmtUpdateService;

    @Mock
    private UserActivityDomainService userActivityDomainService;

    private CreateListCmtRequest createListCmtRequest;
    private DeleteListCmtRequset deleteListCmtRequset;
    private UpdateListCmtRequest updateListCmtRequest;
    private MyListComment myListComment;

    @BeforeEach
    void setUp() {
        createListCmtRequest = new CreateListCmtRequest(1L, "댓글 내용");
        deleteListCmtRequset = new DeleteListCmtRequset(1L, 1L, 4L);
        updateListCmtRequest = new UpdateListCmtRequest(1L, 1L, "수정된 댓글 내용");
        myListComment = new MyListComment(1L, 1L, "댓글 내용", 4L);
    }

    @Test
    void createListCmt_ShouldReturnListCommentSeq_WhenCommentIsCreated() {
        when(listCmtDomainRepository.save(any(MyListComment.class))).thenReturn(myListComment);

        Long result = listCmtCommandService.createListCmt(createListCmtRequest);

        assertEquals(1L, result);
        verify(userActivityDomainService).updateUserActivityPoint(4L, 1);
        verify(listCmtDomainRepository).save(any(MyListComment.class));
    }

    @Test
    void deleteListCmt_ShouldDeleteCommentAndUpdateUserActivityPoint() {
        when(listCmtDomainRepository.findByListCommentSeq(deleteListCmtRequset.getListCommentSeq())).thenReturn(myListComment);

        listCmtCommandService.deleteListCmt(deleteListCmtRequset);

        verify(listCmtDomainRepository).deleteById(deleteListCmtRequset.getListCommentSeq());
        verify(userActivityDomainService).updateUserActivityPoint(myListComment.getListCommentUserSeq(), -1);
    }

    @Test
    void updateListCmt_ShouldReturnUpdatedCommentSeq_WhenCommentIsUpdated() {
        when(domainListCmtUpdateService.updateListCmt(eq(updateListCmtRequest), anyLong())).thenReturn(1L);

        Long result = listCmtCommandService.updateListCmt(updateListCmtRequest);

        assertEquals(1L, result);
        verify(domainListCmtUpdateService).updateListCmt(eq(updateListCmtRequest), anyLong());
    }
}
