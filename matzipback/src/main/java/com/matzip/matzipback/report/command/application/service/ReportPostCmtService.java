package com.matzip.matzipback.report.command.application.service;

import com.matzip.matzipback.board.command.domain.aggregate.PostComment;
import com.matzip.matzipback.board.command.domain.service.PostCommentDomainService;
import com.matzip.matzipback.report.command.domain.service.ReportDomainService;
import com.matzip.matzipback.report.command.dto.PtAndCmtReportReqDTO;
import org.springframework.stereotype.Service;

@Service
public class ReportPostCmtService {

    PostCommentDomainService postCommentDomainService;
    ReportDomainService reportDomainService;

    // 게시글 댓글 신고 기능
    public void savePostCmtReport(PtAndCmtReportReqDTO postCmtReportReqDTO) {
        // 댓글 가져오기
        PostComment foundPostComment = postCommentDomainService
                .findByPostCommentSeq(postCmtReportReqDTO.getPostCommentSeq());
        // DTO 값 넣어주기
        postCmtReportReqDTO.setPostSeq(foundPostComment.getPostSeq());
        postCmtReportReqDTO.setReporterUserSeq(/*CustomUserUtils.getCurrentUserSeq();*/ 2L);
        postCmtReportReqDTO.setReportedUserSeq(foundPostComment.getPostCommentUserSeq());
        // 이미 신고된 게시글 댓글인지 확인
        reportDomainService.checkReportExists(postCmtReportReqDTO);
        // 확인된 게시글 댓글 신고 저장
        reportDomainService.saveReport(postCmtReportReqDTO);
    }
}
