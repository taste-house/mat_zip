package com.matzip.matzipback.sercurity.config;

import com.matzip.matzipback.sercurity.filter.JwtFilter;
import com.matzip.matzipback.sercurity.handler.JwtAccessDeniedHandler;
import com.matzip.matzipback.sercurity.handler.JwtAuthenticationEntryPoint;
import com.matzip.matzipback.sercurity.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtUtil jwtUtil;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        /* CSRF 토큰 발행 시 Client 에서 매번 해당 토큰도 함께 넘겨주어야 하므로 기능 비 활성화 */
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auths -> {
                    // 회원가입 Post 는 인증 필요 없음
                    auths.requestMatchers(
                            new AntPathRequestMatcher("/back/api/vi/auth/register"),
                            new AntPathRequestMatcher("/back/api/vi/**"),
                            new AntPathRequestMatcher("/**"),
                            new AntPathRequestMatcher("/swagger-ui/index.html"),
                            new AntPathRequestMatcher("/swagger-ui/**"),
                            new AntPathRequestMatcher("/v3/api-docs/**")
                            ).permitAll()
                            .anyRequest().authenticated();
                })
                /* session 로그인 방식을 사용하지 않음 (JWT Token 방식을 사용할 예정) */
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );



        /* 커스텀 로그인 필터 이전에 JWT 토큰 확인 필터를 설정 */
        http.addFilterBefore(new JwtFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        /* 인증, 인가 실패 핸들러 설정 */
        http.exceptionHandling(
                exceptionHandling -> {
                    exceptionHandling.accessDeniedHandler(new JwtAccessDeniedHandler());
                    exceptionHandling.authenticationEntryPoint(new JwtAuthenticationEntryPoint());
                }
        );

        return http.build();

    }
}
