package com.matzip.matzipuser.matzipList.command.mapper;

import com.matzip.matzipuser.matzipList.command.application.dto.CreateListCmtRequest;
import com.matzip.matzipuser.matzipList.command.domain.aggregate.MyListComment;

public class ListCmtMapper {
    public static MyListComment toEntity(CreateListCmtRequest listCmtRequest, Long listCommentUserSeq) {
        return MyListComment.create(
                listCmtRequest.getListSeq(),
                listCommentUserSeq,
                listCmtRequest.getListCommentContent()
        );
    }
}
