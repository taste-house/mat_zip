package com.matzip.matzipback.sercurity.util;

import com.matzip.matzipback.users.command.domain.aggregate.Users;
import com.matzip.matzipback.users.command.domain.repository.UsersDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UsersDomainRepository UsersDomainRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {

        Users loginUser = UsersDomainRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException(userEmail));

        // 권한 저장
        List<GrantedAuthority> userAuthorities = new ArrayList<>();
        userAuthorities.add(new SimpleGrantedAuthority(loginUser.getUserAuth()));

        return new User(String.valueOf(loginUser.getUserSeq()), loginUser.getUserPassword(), userAuthorities);
    }
}
