package com.matzip.matzipback.test.command.services;


import com.matzip.matzipback.test.command.entities.Test;
import com.matzip.matzipback.test.command.repositories.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;


    /* Test Post 작업*/
    public TestDTO createTest(String content){

        Test test = new Test(content);
        Test savedTest = testRepository.save(test);
        return new TestDTO(savedTest.getContent());
    }

    public TestDTO updateTest(Long id, String content) {
        Test test = testRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 데이터가 존재하지 않습니다 : " + id));

        test.setContent(content);  // 업데이트된 content 설정
        Test updatedTest = testRepository.save(test);  // 업데이트 후 저장
        return new TestDTO(updatedTest.getContent());  // 업데이트된 DTO 반환
    }

    // Test Delete
    public boolean deleteTest(Long id) {
        if (testRepository.existsById(id)) {
            testRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
