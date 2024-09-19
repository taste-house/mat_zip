package com.matzip.matzipback.test.query.services;

import com.mamully.toyProject.test.command.services.TestDTO;
import com.mamully.toyProject.test.query.mappers.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestQueryService {

    @Autowired
    private TestMapper testMapper;

    // 모든 Test 데이터를 조회하는 메서드
    public List<TestDTO> getAllTests() {
        return testMapper.findAllTests();
    }

    // 특정 ID로 Test 데이터를 조회하는 메서드
    public TestDTO getTestById(Long id) {
        TestDTO testDTO = testMapper.findTestById(id);
        if (testDTO == null) {
            throw new IllegalArgumentException("해당 ID의 데이터가 존재하지 않습니다 : " + id);
        }
        return testDTO;
    }
}
