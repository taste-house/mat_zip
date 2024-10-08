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
    public static Long getCurrentUserSeq() {
        return getCurrentUserDetails()
                .map(UserDetails::getUsername) // 유저 이메일 가져오기
                .map(username -> {
                    try {
                        return Long.parseLong(username); // Long으로 변환
                    } catch (NumberFormatException e) {
                        return null; // 변환 실패 시 null 반환
                    }
                })
                .orElse(null); // UserDetails가 없을 경우 null 반환
    }
}
