package com.matzip.matzipuser.matzipList.command.mapper;

import com.matzip.matzipuser.matzipList.command.application.dto.CreateListRequest;
import com.matzip.matzipuser.matzipList.command.domain.aggregate.MyList;

public class ListMapper {
    public static MyList toEntity(CreateListRequest listRequest, Long listUserSeq, int listLevel) {
        return MyList.create(
                listUserSeq,
                listRequest.getListTitle(),
                listRequest.getListContent(),
                listLevel
        );
    }
}
