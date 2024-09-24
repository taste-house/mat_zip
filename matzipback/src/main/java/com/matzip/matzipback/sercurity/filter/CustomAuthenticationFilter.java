package com.matzip.matzipback.sercurity.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matzip.matzipback.sercurity.dto.LoginRequestDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;
import java.util.ArrayList;

public class CustomAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    // "api/v1/auth/login, "POST" 을 가로챌거임
    public CustomAuthenticationFilter() {
        super(new AntPathRequestMatcher("/api/v1/auth/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        // LoginRequestDTO 로 변환 과정 -> 받은 값으로 인증 할거임
        // ObjectMapper 로 요청 메시지의 바디 내용을 내가 원하는 객체로 변환함
        LoginRequestDTO loginRequestDTO = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDTO.class);

        // 로그인 인증을 함
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getUserEmail(), loginRequestDTO.getUserPassword(), new ArrayList<>())
        );
    }
}
