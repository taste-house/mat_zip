package com.matzip.matzipback.report.command.domain.aggregate;

import com.matzip.matzipback.board.command.domain.aggregate.Post;
import com.matzip.matzipback.board.command.domain.aggregate.PostComment;
import com.matzip.matzipback.matzipList.command.domain.aggregate.MyList;
import com.matzip.matzipback.matzipList.command.domain.aggregate.MyListComment;
import com.matzip.matzipback.matzipList.command.domain.aggregate.Review;
import com.matzip.matzipback.users.command.domain.aggregate.Messages;
import com.matzip.matzipback.users.command.domain.aggregate.Users;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "report")
@NoArgsConstructor/*(access = AccessLevel.PROTECTED)*/
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reportSeq;

    /*@ManyToOne
    @JoinColumn(name = "reporter_user_seq", referencedColumnName = "userSeq")
    private Users reporterUserSeq; // 신고한 회원

    @ManyToOne
    @JoinColumn(name = "reported_user_seq", referencedColumnName = "userSeq")
    private Users reportedUserSeq; // 신고당한 회원

    @ManyToOne
    @JoinColumn(name = "penalty_seq", referencedColumnName = "penaltySeq")
    private Penalty penaltySeq;*/

    private long reporterUserSeq;
    private long reportedUserSeq;
    private long penaltySeq;

//    @OneToMany(mappedBy = "report")
//    private List<ReportReason> reportReasons = new ArrayList<>();

    @CreatedDate
    private LocalDateTime reportTime;
    private String reportContent;
    private String reportProceedingYn;
    private LocalDateTime reportProceedingTime;
    private String reportProceedingResult;

    private long postSeq;
    private long postCommentSeq;
    private long listSeq;
    private long listCommentSeq;
    private long messageSeq;
    private long reviewSeq;


    /*@ManyToOne
    @JoinColumn(name = "post_seq")
    private Post postSeq; // 게시글과의 관계

    @ManyToOne
    @JoinColumn(name = "post_comment_seq")
    private PostComment postCommentSeq; // 게시글 댓글과의 관계

    @ManyToOne
    @JoinColumn(name = "list_seq")
    private MyList listSeq; // 리스트와의 관계

    @ManyToOne
    @JoinColumn(name = "list_comment_seq")
    private MyListComment listCommentSeq; // 리스트 댓글과의 관계\

    @ManyToOne
    @JoinColumn(name = "message_seq")
    private Messages messageSeq;

    @ManyToOne
    @JoinColumn(name = "review_seq")
    private Review reviewSeq;*/

}
