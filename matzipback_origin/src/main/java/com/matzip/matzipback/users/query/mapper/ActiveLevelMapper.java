package com.matzip.matzipback.users.query.mapper;

import com.matzip.matzipback.users.query.dto.ActiveLevelDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ActiveLevelMapper {

    List<ActiveLevelDTO> findAllActiveLevel(@Param("offset") long offset, @Param("size") int size);

    long countAllActiveLevel();

}
