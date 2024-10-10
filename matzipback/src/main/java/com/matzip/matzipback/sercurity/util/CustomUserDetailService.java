package com.matzip.matzipback.sercurity.util;

import com.matzip.matzipback.responsemessage.SuccessSearchResMessage;
import com.matzip.matzipback.sercurity.dto.UsersDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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

    private final UserFeignClient userFeignClient;
    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {

        // Email로 로그인 후에 토큰에 user_seq 값이 저장되어 이후에는 Email로 조회가 불가능한 문제 발생
        // 임시로 try - catch로 해결하고 추후에 깔끔하게 해결 필요
        UsersDTO loginUser;
        try {
            SuccessSearchResMessage<?> response = userFeignClient.getUserByEmail(userEmail);

            if (response.getData2() == null) throw new UsernameNotFoundException("해당 유저가 존재하지 않습니다.");

            loginUser = modelMapper.map(response.getData2(), UsersDTO.class);

        } catch (Exception e) {
            log.info("이미 로그인 된 유저, seq(" + e.getMessage() + ")로 검색");
            SuccessSearchResMessage<?> response= userFeignClient.getUserByUserSeq(Long.parseLong(userEmail));
            if (response.getData2() == null) throw new UsernameNotFoundException("해당 유저가 존재하지 않습니다.");

            loginUser = modelMapper.map(response.getData2(), UsersDTO.class);
        }

        // 권한 저장
        List<GrantedAuthority> userAuthorities = new ArrayList<>();
        userAuthorities.add(new SimpleGrantedAuthority(loginUser.getUserAuth()));

        return new User(String.valueOf(loginUser.getUserSeq()), loginUser.getUserPassword(), userAuthorities);
    }
}
