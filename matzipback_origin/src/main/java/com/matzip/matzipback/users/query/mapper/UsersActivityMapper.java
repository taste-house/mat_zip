package com.matzip.matzipback.users.query.mapper;

import com.matzip.matzipback.users.query.dto.UsersActivityDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UsersActivityMapper {

    long countAllUsersActivity(@Param("active") String active);

    List<UsersActivityDTO> searchAllUsersActivity(@Param("offset") long offset, @Param("size") int size, @Param("active") String active);
}
