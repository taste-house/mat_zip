package com.matzip.matzipback.test.query.mappers;

import com.matzip.matzipback.test.command.services.TestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {
    List<TestDTO> findAllTests();

    TestDTO findTestById(Long id);
}
