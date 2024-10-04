package com.matzip.matzipuser.users.query.mapper;

import com.matzip.matzipuser.users.query.dto.ActiveLevelDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ActiveLevelMapper {

    List<ActiveLevelDTO> findAllActiveLevel(@Param("offset") long offset, @Param("size") int size);

    long countAllActiveLevel();

}
