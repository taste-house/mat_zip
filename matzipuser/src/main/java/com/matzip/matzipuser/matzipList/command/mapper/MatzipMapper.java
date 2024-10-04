package com.matzip.matzipuser.matzipList.command.mapper;

import com.matzip.matzipuser.matzipList.command.application.dto.CreateMatzipRequest;
import com.matzip.matzipuser.matzipList.command.domain.aggregate.MyListMatzip;

public class MatzipMapper {
    public static MyListMatzip toEntity(CreateMatzipRequest matzipRequest) {
        return MyListMatzip.create(
                matzipRequest.getListSeq(),
                matzipRequest.getRestaurantSeq(),
                matzipRequest.getListMatzipComment()

        );
    }
}
