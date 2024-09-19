package com.matzip.matzipback.test.query.controllers;


import com.matzip.matzipback.test.command.services.TestDTO;
import com.matzip.matzipback.test.query.services.TestQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/test")
public class TestQueryController {

    @Autowired
    private TestQueryService testQueryService;

    // 모든 Test 데이터를 조회
    @GetMapping
    public ResponseEntity<List<TestDTO>> getAllTests() {
        List<TestDTO> tests = testQueryService.getAllTests();
        return ResponseEntity.ok(tests);
    }

    // 특정 ID로 Test 데이터를 조회
    @GetMapping("/{id}")
    public ResponseEntity<TestDTO> getTestById(@PathVariable Long id) {
        TestDTO test = testQueryService.getTestById(id);
        return ResponseEntity.ok(test);
    }
}
