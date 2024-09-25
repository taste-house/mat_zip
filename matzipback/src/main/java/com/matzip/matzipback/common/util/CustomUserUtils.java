package com.matzip.matzipback.common.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class CustomUserUtils {

    // 현재 인증된 사용자의 UserDetails 반환
    public static Optional<UserDetails> getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return Optional.of((UserDetails) authentication.getPrincipal());
        }
        return Optional.empty();
    }

    // 현재 인증된 사용자의 권한 반환
    public static Collection<? extends GrantedAuthority> getCurrentUserAuthorities() {
        return getCurrentUserDetails()
                .map(UserDetails::getAuthorities)
                .orElse(null);
    }

    // 현재 인증된 사용자의 username 반환
    public static String getCurrentUserEmail() {
        return getCurrentUserDetails()
                .map(UserDetails::getUsername) // 유저 이메일 빼오기
                .orElse(null);
    }
}
