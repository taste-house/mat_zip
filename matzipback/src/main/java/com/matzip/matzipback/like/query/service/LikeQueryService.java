package com.matzip.matzipback.like.query.service;

import com.matzip.matzipback.exception.ErrorCode;
import com.matzip.matzipback.exception.RestApiException;
import com.matzip.matzipback.like.query.dto.LikedPostDTO;
import com.matzip.matzipback.like.query.mapper.LikeQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeQueryService {

    private final LikeQueryMapper likeQueryMapper;

    // 1차 수정 아직 덜됨 필요한 메서드인지 나중에라도 판단할 필요있음 - 창윤
    // 유저 번호랑 게시글 댓글 번호로 좋아요 객체 찾기
    public Long findLikeByUserSeqAndPostCommentSeq(Long userSeq, Long postCommentSeq) {
        return likeQueryMapper.findLikeByUserSeqAndPostCommentSeq(userSeq, postCommentSeq);
    }

    // 1차 수정 - 창윤
    // 나의 좋아요한 게시글 목록 찾기
    public List<LikedPostDTO> findMyLikedPost(Long userSeq, long page) {


//        long loginUserSeq = CustomUserUtils.getCurrentUserSeq();

        long loginUserSeq = 2L;

        // 조회하려는 유저가 로그인한 회원과 다르다면 에러 던지기
        if (loginUserSeq != userSeq) {
            throw new RestApiException(ErrorCode.UNAUTHORIZED_REQUEST);
        }

        // 10개씩 보여주기
        int size = 10;
        // 시작점 : offset
        long offset = (page - 1) * size;

        // 내가 좋아요한 게시글의 총 개수
        long count = likeQueryMapper.countMyLikePost(userSeq);

        if (offset > count) {
            page = count / size + 1;
            offset = (page - 1) * size;
        }

        return likeQueryMapper.findMyLikedPost(userSeq, offset, size);
    }
}
