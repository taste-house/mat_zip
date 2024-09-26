package com.matzip.matzipback.users.command.application.service;

import com.matzip.matzipback.users.command.application.utility.UUIDGenerator;
import com.matzip.matzipback.users.command.domain.aggregate.Users;
import com.matzip.matzipback.users.command.domain.repository.UsersDomainRepository;
import com.matzip.matzipback.users.command.dto.CreateUserRequest;
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
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder; // 비밀번호 암호화
    private final EmailService emailService; // 이메일 인증 확인을 위한 서비스
    private final UUIDGenerator uuidGenerator; // 임의 닉네임 자동생성을 위한 유틸리티

    public void createUser(CreateUserRequest newUser) {
        log.info("========create user========");

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
    }

    private String createUniqueNickname(){
        log.info("========닉네임 중복체크 후 생성========");
        String nickname;
        do {
            nickname = uuidGenerator.createRandomNickname();
        } while (usersDomainRepository.existsByUserNickname(nickname));
        return nickname;
    }

}
