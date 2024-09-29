package com.matzip.matzipback.matzipList.command.mapper;

import com.matzip.matzipback.matzipList.command.application.dto.CreateListCmtRequest;
import com.matzip.matzipback.matzipList.command.domain.aggregate.MyListComment;

public class ListCmtMapper {
    public static MyListComment toEntity(CreateListCmtRequest listCmtRequest, Long listCommentUserSeq) {
        return MyListComment.create(
                listCmtRequest.getListSeq(),
                listCommentUserSeq,
                listCmtRequest.getListCommentContent()
        );
    }
}
