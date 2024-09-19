package com.matzip.matzipback.test.command.controllers;

import com.matzip.matzipback.test.command.services.TestDTO;
import com.matzip.matzipback.test.command.services.TestService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/test")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    // POST
    @PostMapping
    public ResponseEntity<TestDTO> createTest(@RequestBody TestDTO testDTO) {
        // 서비스 호출하여 테스트 생성
        TestDTO createdTestDTO = testService.createTest(testDTO.getContent());
        return ResponseEntity.ok(createdTestDTO);
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<TestDTO> updateTest(@PathVariable Long id, @RequestBody TestDTO testDTO) {
        TestDTO updatedTestDTO = testService.updateTest(id, testDTO.getContent());
        if (updatedTestDTO != null) {
            return ResponseEntity.ok(updatedTestDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTest(@PathVariable Long id) {
        boolean isDeleted = testService.deleteTest(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
