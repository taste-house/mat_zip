package com.matzip.matzipuser.matzipList.query.mapper;

import com.matzip.matzipuser.matzipList.query.dto.ListContentDTO;
import com.matzip.matzipuser.matzipList.query.dto.ListSearchAllDTO;
import com.matzip.matzipuser.matzipList.query.dto.ListSearchUserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ListQueryMapper {

    List<ListSearchAllDTO> getListBox(long listUserSeq);

    List<ListSearchUserDTO> getUserListBox(Long listUserSeq);


    List<ListContentDTO> getListContests(Long listSeq);

}
