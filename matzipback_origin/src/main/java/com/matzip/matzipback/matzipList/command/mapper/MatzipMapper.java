package com.matzip.matzipback.matzipList.command.mapper;

import com.matzip.matzipback.matzipList.command.application.dto.CreateMatzipRequest;
import com.matzip.matzipback.matzipList.command.domain.aggregate.MyListMatzip;

public class MatzipMapper {
    public static MyListMatzip toEntity(CreateMatzipRequest matzipRequest) {
        return MyListMatzip.create(
                matzipRequest.getListSeq(),
                matzipRequest.getRestaurantSeq(),
                matzipRequest.getListMatzipComment()

        );
    }
}
