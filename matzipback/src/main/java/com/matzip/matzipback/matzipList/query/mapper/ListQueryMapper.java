package com.matzip.matzipback.matzipList.query.mapper;

import com.matzip.matzipback.matzipList.query.dto.ListCategoryDTO;
import com.matzip.matzipback.matzipList.query.dto.ListSearchAllDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ListQueryMapper {

    List<ListSearchAllDTO> getListBox(long listUserSeq);


    
    Long getCountList(long listUserSeq); // 각 사용자의 리스트 개수 구하기



}
