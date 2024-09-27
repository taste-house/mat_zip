package com.matzip.matzipback.matzipList.query.mapper;

import com.matzip.matzipback.matzipList.query.dto.MatzipSearchDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MatzipQueryMapper {

    List<MatzipSearchDTO> getMatzip(Long listSeq, String matzipTitle);
}
