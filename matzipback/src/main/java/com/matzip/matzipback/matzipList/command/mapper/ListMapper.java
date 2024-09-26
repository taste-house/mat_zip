package com.matzip.matzipback.matzipList.command.mapper;

import com.matzip.matzipback.matzipList.command.application.dto.CreateListRequest;
import com.matzip.matzipback.matzipList.command.domain.aggregate.MyList;

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
