package com.matzip.matzipback.report.command.application.service;

import com.matzip.matzipback.board.command.domain.aggregate.Post;
import com.matzip.matzipback.board.command.domain.aggregate.PostComment;
import com.matzip.matzipback.board.command.domain.repository.PostCommentRepository;
import com.matzip.matzipback.exception.RestApiException;
import com.matzip.matzipback.matzipList.command.domain.aggregate.MyList;
import com.matzip.matzipback.matzipList.command.domain.aggregate.MyListComment;
import com.matzip.matzipback.matzipList.command.domain.repository.ListCmtDomainRepository;
import com.matzip.matzipback.report.command.domain.aggregate.Penalty;
import com.matzip.matzipback.report.command.domain.aggregate.Report;
import com.matzip.matzipback.report.command.domain.aggregate.ReportView;
import com.matzip.matzipback.matzipList.command.domain.repository.ListDomainRepository;
import com.matzip.matzipback.report.command.domain.repository.PenaltyRepository;
import com.matzip.matzipback.report.command.domain.repository.ReportDomainRepository;
import com.matzip.matzipback.report.command.domain.repository.ReportViewRepository;
import com.matzip.matzipback.report.command.dto.PenaltyUpdateRequest;
import com.matzip.matzipback.report.command.dto.ReportAndPenaltyDTO;
import com.matzip.matzipback.board.command.domain.repository.PostRepository;
import com.matzip.matzipback.review.command.domain.aggregate.Review;
import com.matzip.matzipback.review.command.domain.repository.ReviewRepository;
import com.matzip.matzipback.users.command.domain.aggregate.Users;
import com.matzip.matzipback.users.command.domain.repository.UsersDomainRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.matzip.matzipback.exception.ErrorCode.NOT_FOUND;
import static com.matzip.matzipback.users.command.domain.aggregate.UserStatus.active;
import static com.matzip.matzipback.users.command.domain.aggregate.UserStatus.inactive;

@Service
@RequiredArgsConstructor
public class PenaltyCommandService {

    private final PenaltyRepository penaltyRepository;
    private final ModelMapper modelMapper;
    private final ReportViewRepository reportViewRepository;
    private final ReportDomainRepository reportDomainRepository;
    private final PostRepository postRepository;
    private final PostCommentRepository postCommentRepository;
    private final ListDomainRepository listDomainRepository;
    private final ListCmtDomainRepository listCmtDomainRepository;
    private final ReviewRepository reviewRepository;
    private final UsersDomainRepository usersDomainRepository;

    /* 1. 패널티 등록 */
    @Transactional
    public Long createPenalty(ReportAndPenaltyDTO newPenalty) {

        // DTO -> Entity
        Penalty penalty = modelMapper.map(newPenalty, Penalty.class);

        // Penalty 저장 후 반환 받은 Penalty Entity에서 penaltySeq 추출
        Penalty savedPenalty = penaltyRepository.save(penalty);
        Long penaltySeq = savedPenalty.getPenaltySeq();

        // Category, Seq로 repost_seq 가져온 뒤 penalty_seq에 값 삽입
        List<ReportView> reportViewList = reportViewRepository.findByCategoryAndSeq(newPenalty.getCategory(), newPenalty.getSeq());
        System.out.println("reportViewList: " + reportViewList);

        Long reportSeq;

        // report 테이블 업데이트
       for(ReportView report : reportViewList) {
            reportSeq = report.getReportSeq();
            Report foundReport = reportDomainRepository.findById(reportSeq)
                    .orElseThrow(() -> new RestApiException(NOT_FOUND));
            foundReport.updateReportDetails(penaltySeq);
        }

       // 신고 당한 해당 컨텐츠 비활성화 설정 (메세지는 기능 완성되면 추후 설정)
       switch(newPenalty.getCategory()){
           case "post" :
               Post post = postRepository.findById(newPenalty.getSeq()).orElseThrow(() -> new RestApiException(NOT_FOUND));
               post.updatePostStatus("inactive");
               break;
           case "postComment" :
               PostComment postcomment = postCommentRepository.findById(newPenalty.getSeq()).orElseThrow(() -> new RestApiException(NOT_FOUND));
               postcomment.updatepostCmtStatus("inactive");
               break;
           case "list" :
               MyList list = listDomainRepository.findById(newPenalty.getSeq()).orElseThrow(() -> new RestApiException(NOT_FOUND));
               list.updateListStatus("inactive");
               break;
           case "listComment" :
               MyListComment listComment = listCmtDomainRepository.findById(newPenalty.getSeq()).orElseThrow(() -> new RestApiException(NOT_FOUND));
               listComment.updateListCmtStatus("inactive");
               break;
           case "review" :
               Review review = reviewRepository.findById(newPenalty.getSeq()).orElseThrow(() -> new RestApiException(NOT_FOUND));
               review.updateReviewStatus("inactive");
               break;
/*           case "message" :
               Message post = postRepository.findById(newPenalty.getSeq()).orElseThrow(() -> new RestApiException(NOT_FOUND));
               post.updatePostStatus("inactive");
               break;   */
           default :
               throw new RestApiException(NOT_FOUND);
       }

       //유저 상태 inactive 비활성화 설정
        Users user = usersDomainRepository.findById(newPenalty.getPenaltyUserSeq()).orElseThrow(() -> new RestApiException(NOT_FOUND));
       user.updateUserStatus(inactive);

        return penaltySeq;
    }

    /* 2. 패널티 수정 */
    @Transactional
    public void updatePenalty(Long penaltySeq, PenaltyUpdateRequest updatePenalty) {

        Penalty foundPenalty = penaltyRepository.findById(penaltySeq).orElseThrow(() -> new RestApiException(NOT_FOUND));

        foundPenalty.updatePenaltyDetails(
                updatePenalty.getPenaltyStartDate(),
                updatePenalty.getPenaltyEndDate(),
                updatePenalty.getPenaltyType(),
                updatePenalty.getPenaltyReasonContent()
        );

    }

    /* 3. 패널티 철회 */
    @Transactional
    public void deletePenalty(Long penaltySeq) {

        // 전달 된 penaltySeq로 Penalty Entity 조회
        Penalty penalty = penaltyRepository.findById(penaltySeq).orElseThrow(() -> new RestApiException(NOT_FOUND));
    
        // penalty 철회
        penaltyRepository.deleteById(penaltySeq);

        //유저 상태 inactive -> active 설정
        Users user = usersDomainRepository.findById(penalty.getPenaltyUserSeq()).orElseThrow(() -> new RestApiException(NOT_FOUND));
        user.updateUserStatus(active);
    
    }

}
