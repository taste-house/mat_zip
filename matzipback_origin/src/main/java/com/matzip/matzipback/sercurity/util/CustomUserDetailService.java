package com.matzip.matzipback.sercurity.util;

import com.matzip.matzipback.users.command.domain.aggregate.Users;
import com.matzip.matzipback.users.command.domain.repository.UsersDomainRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UsersDomainRepository UsersDomainRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {

        // Email로 로그인 후에 토큰에 user_seq 값이 저장되어 이후에는 Email로 조회가 불가능한 문제 발생
        // 임시로 try - catch로 해결하고 추후에 깔끔하게 해결 필요
        Users loginUser;
        try {
            loginUser = UsersDomainRepository.findByUserEmail(userEmail)
                    .orElseThrow(() -> new UsernameNotFoundException(userEmail));
        } catch (Exception e) {
            log.info("이미 로그인 된 유저, seq(" + e.getMessage() + ")로 검색");
            loginUser = UsersDomainRepository.findByUserSeq(Long.parseLong(userEmail))
                    .orElseThrow(() -> new UsernameNotFoundException(userEmail));
        }

        // 권한 저장
        List<GrantedAuthority> userAuthorities = new ArrayList<>();
        userAuthorities.add(new SimpleGrantedAuthority(loginUser.getUserAuth()));

        return new User(String.valueOf(loginUser.getUserSeq()), loginUser.getUserPassword(), userAuthorities);
    }
}
