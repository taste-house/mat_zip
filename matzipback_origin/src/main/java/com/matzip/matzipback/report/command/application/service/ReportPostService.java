package com.matzip.matzipback.report.command.application.service;

import com.matzip.matzipback.board.command.domain.aggregate.Post;
import com.matzip.matzipback.board.command.domain.service.PostCommentDomainService;
import com.matzip.matzipback.board.command.domain.service.PostDomainService;
import com.matzip.matzipback.common.util.CustomUserUtils;
import com.matzip.matzipback.report.command.domain.service.ReportDomainService;
import com.matzip.matzipback.report.command.dto.PtAndCmtReportReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportPostService {

    private final PostDomainService postDomainService;
    private final ReportDomainService reportDomainService;
    private final PostCommentDomainService postCommentDomainService;

    // 게시글 신고 기능
    public void savePostReport(PtAndCmtReportReqDTO ptAndCmtReportReqDTO) {
        // 게시글 가져오기
        Post foundPost = postDomainService.findByPostSeq(ptAndCmtReportReqDTO.getPostSeq());
        // DTO 값 넣어주기
        ptAndCmtReportReqDTO.setReporterUserSeq(CustomUserUtils.getCurrentUserSeq());
        ptAndCmtReportReqDTO.setReportedUserSeq(foundPost.getPostUserSeq());
        // 이미 신고된 게시글 댓글인지 확인
        reportDomainService.checkReportExists(ptAndCmtReportReqDTO);
        // 확인된 게시글 댓글 신고 저장
        reportDomainService.saveReport(ptAndCmtReportReqDTO);
    }

}
