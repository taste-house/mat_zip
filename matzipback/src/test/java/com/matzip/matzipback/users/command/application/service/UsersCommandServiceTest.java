package com.matzip.matzipback.users.command.application.service;

import com.matzip.matzipback.exception.RestApiException;
import com.matzip.matzipback.users.command.domain.aggregate.UserStatus;
import com.matzip.matzipback.users.command.domain.aggregate.Users;
import com.matzip.matzipback.users.command.domain.repository.UsersDomainRepository;
import com.matzip.matzipback.users.command.dto.CreateUserRequest;
import com.matzip.matzipback.users.command.dto.DeleteUserRequest;
import com.matzip.matzipback.users.command.dto.UpdateUserRequest;
import jakarta.persistence.EntityManager;
import org.apache.commons.lang3.Validate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.matzip.matzipback.exception.ErrorCode.NOT_FOUND;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UsersCommandServiceTest {

    @Autowired
    private UsersCommandService usersCommandService;

    @Autowired
    private UsersDomainRepository usersDomainRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @MockBean
    private EmailService emailService;

    @DisplayName("회원가입 테스트-이메일인증 가정")
    @Test
    void createUser() {
        // given
        CreateUserRequest request = new CreateUserRequest();
        request.setUserEmail("test@gmail.com");
        request.setUserPassword("password123!");
        request.setUserName("사용자");
        request.setUserPhone("01012345678");

        // 이메일 인증이 완료된 상태로 설정
        when(emailService.isEmailVerified(request.getUserEmail())).thenReturn(true);    // isEmailVerified 가 실행되면 강제로 true로 설정

        // when
        usersCommandService.createUser(request);

        // then
        assertTrue(usersDomainRepository.existsByUserEmail(request.getUserEmail()));
        assertNotNull(usersDomainRepository.findByUserEmail(request.getUserEmail()));
    }

    @DisplayName("회원정보 수정 테스트")
    @Test
    void updateUserInfo() {
        // given
        UpdateUserRequest updateUserInfo = new UpdateUserRequest();
        updateUserInfo.setUserSeq(17L);
        updateUserInfo.setUserNickname("newNickname");
        updateUserInfo.setUserPassword("newPassword123!");
        updateUserInfo.setUserPhone("01012345678");

        // when
        usersCommandService.updateUserInfo(updateUserInfo);

        // then
        Users updatedUser = usersDomainRepository.findById(17L).orElseThrow();
        assertEquals("newNickname", updatedUser.getUserNickname());
        assertTrue(passwordEncoder.matches("newPassword123!", updatedUser.getUserPassword()));
        assertEquals("01012345678", updatedUser.getUserPhone());
    }

    @Test
    @DisplayName("회원탈퇴 테스트 - status가 inactive 로 변경됨")
    void deleteUser() {
        // given
        DeleteUserRequest deleteUserInfo = new DeleteUserRequest();
        deleteUserInfo.setUserSeq(17L);
        deleteUserInfo.setUserPassword("password123!");

        Users existingUser = usersDomainRepository.findById(17L)
                .orElseThrow(() -> new RestApiException(NOT_FOUND));   // 조회 된 회원이 없을 경우
        // 비밀번호가 일치하는지 확인
        assertTrue(passwordEncoder.matches("password123!", existingUser.getUserPassword()));
        System.out.println("회원 ID: " + existingUser.getUserSeq());
        System.out.println("회원 이메일: " + existingUser.getUserEmail());
        System.out.println("회원 상태: " + existingUser.getUserStatus());

        // when
        System.out.println("회원 상태 변경 전: "+ existingUser.getUserStatus());
        usersCommandService.deleteUser(deleteUserInfo);
//        existingUser.updateStatus(UserStatus.inactive);
        System.out.println("회원 상태 변경 후: "+ existingUser.getUserStatus());

        // then
        assertEquals(UserStatus.inactive, existingUser.getUserStatus(), "회원 상태가 inactive 로 변경");   // 실패뜸,,
    }
}