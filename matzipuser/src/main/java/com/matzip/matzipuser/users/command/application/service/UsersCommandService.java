package com.matzip.matzipuser.users.command.application.service;

import com.matzip.matzipuser.users.command.application.utility.UUIDGenerator;
import com.matzip.matzipuser.users.command.domain.aggregate.UserActivity;
import com.matzip.matzipuser.users.command.domain.aggregate.Users;
import com.matzip.matzipuser.users.command.domain.repository.UsersDomainRepository;
import com.matzip.matzipuser.users.command.domain.service.UserActivityDomainService;
import com.matzip.matzipuser.users.command.dto.CreateUserRequest;
import com.matzip.matzipuser.users.command.dto.DeleteUserRequest;
import com.matzip.matzipuser.users.command.dto.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UsersCommandService {

    private final UsersDomainRepository usersDomainRepository;
    private final ModelMapper modelMapper;  // dto와 entity 변환
  
    private final BCryptPasswordEncoder passwordEncoder; // 비밀번호 암호화
    private final EmailService emailService; // 이메일 인증 확인을 위한 서비스
    private final UUIDGenerator uuidGenerator; // 임의 닉네임 자동생성을 위한 유틸리티
    private final UserActivityDomainService userActivityDomainService; // 유저 활동도 서비스

    /* 유저 생성 - 회원가입 */
    public void createUser(CreateUserRequest newUser) {
        log.info("========회원가입 서비스 진입========");

//        if (newUser.getUserPassword() == null || newUser.getUserPassword().isBlank()) {
//            throw new IllegalArgumentException("비밀번호는 필수 입력 사항입니다.");
//        }

        // 이메일 중복체크
        log.info("========이메일 중복체크========");
        if(usersDomainRepository.existsByUserEmail(newUser.getUserEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다. 다른 이메일로 가입해주세요.");
        }

        // 이메일 인증 여부 확인
        log.info("========이메일 인증 확인========");
        if (!emailService.isEmailVerified(newUser.getUserEmail())) {
            throw new IllegalArgumentException("이메일 인증이 완료되지 않았습니다. 인증을 먼저 진행해주세요.");
        }

        // 휴대폰 인증 확인
//        if (!authService.verifyPhoneAuthCode(newUser.getUserPhone())) {
//            throw new IllegalArgumentException("휴대폰 인증에 실패했습니다.");
//        }

        // CreateUserRequest DTO를 Users 엔티티로 변환
        Users users = modelMapper.map(newUser, Users.class);

        // 비밀번호 암호화 후 Users 객체에 설정
        users.encryptPassword(passwordEncoder.encode(newUser.getUserPassword()));
        log.debug("========비밀번호 암호화 완료========");

        // 닉네임 자동 생성
        String nickName = createUniqueNickname();
        users.updateNickname(nickName);

        // Users 엔티티를 데이터베이스에 저장
        usersDomainRepository.save(users);

        log.info("========User 객체 저장 완료 - UserSeq: {}========", users.getUserSeq());

        // 유저 생성 완료 후 인증 상태 제거
        emailService.clearEmailVerificationStatus(newUser.getUserEmail());

        userActivityDomainService.saveUserActivity(UserActivity.create(users.getUserSeq()));
    }

    /* 회원가입시 닉네임 임의자동생성을 위한 메소드 */
    private String createUniqueNickname(){
        log.info("========닉네임 중복체크 후 생성========");
        String nickname;
        do {
            nickname = uuidGenerator.createRandomNickname();
        } while (usersDomainRepository.existsByUserNickname(nickname));
        return nickname;
    }

    /* 회원정보 수정 - 닉네임, 휴대폰, 비밀번호, 사업자 인증(추가) */
    public UpdateUserRequest updateUserInfo(UpdateUserRequest updateUserInfo) {
        log.info("========회원정보 수정 서비스 진입========");

        // 전달 된 userSeq로 Users 엔티티 조회
        Users user = usersDomainRepository.findById(updateUserInfo.getUserSeq())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        log.info("수정 요청 Nickname: {}", updateUserInfo.getUserNickname());
        log.info("수정 전 Nickname: {}", user.getUserNickname());

        // 닉네임 수정
        if (updateUserInfo.getUserNickname() != null && !updateUserInfo.getUserNickname().trim().isEmpty()) {
            if (updateUserInfo.getUserNickname().equals(user.getUserNickname())) {  // 수정 전/후 비교
                throw new IllegalArgumentException("현재 닉네임과 동일한 닉네임으로는 변경할 수 없습니다.");
            }
            if (usersDomainRepository.existsByUserNickname(updateUserInfo.getUserNickname())) { // 클라이언트에게 전송받은 값이 이미 있는 닉네임인지(TRUE) 중복체크
                throw new IllegalArgumentException("이미 사용 중인 닉네임입니다. 다른 닉네임을 선택해주세요.");
            }
            log.info("========닉네임 중복체크 후 수정완료 - UserNickname: {}========", updateUserInfo.getUserNickname());
            user.updateNickname(updateUserInfo.getUserNickname());
        }

        // 비밀번호 수정 (비밀번호가 입력된 경우)
        if (updateUserInfo.getUserPassword() != null && !updateUserInfo.getUserPassword().isBlank()) {
            if (passwordEncoder.matches(updateUserInfo.getUserPassword(), user.getUserPassword())) {
                throw new IllegalArgumentException("현재 비밀번호와 동일한 비밀번호로는 변경할 수 없습니다.");
            }
            // 비밀번호 암호화
            String encryptedPassword = passwordEncoder.encode(updateUserInfo.getUserPassword());
            user.encryptPassword(encryptedPassword);
            log.info("========비밀번호 수정 후 재암호화========");
        }

        // 휴대폰 번호 수정(추가 본인인증은 나중에 구현)
        if (updateUserInfo.getUserPhone() != null) {
            user.updatePhone(updateUserInfo.getUserPhone());
            log.info("========휴대폰 번호 수정완료 - UserPhone: {}========", updateUserInfo.getUserPhone());
        }

        // 사업자 인증

        // Users 엔티티 저장
        Users updatedUser = usersDomainRepository.save(user);

        // 업데이트된 정보를 DTO로 변환하여 반환
        return modelMapper.map(updatedUser, UpdateUserRequest.class);
    }

    public void deleteUser(DeleteUserRequest deleteUserInfo) {
        log.info("========회원탈퇴 서비스 진입========");

        // 전달 된 userSeq로 Users 엔티티 조회
        Users user = usersDomainRepository.findById(deleteUserInfo.getUserSeq())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 비밀번호 검증
        if (!passwordEncoder.matches(deleteUserInfo.getUserPassword(), user.getUserPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        usersDomainRepository.delete(user);

    }

}
