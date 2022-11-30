package com.jitlee.shop.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration //빈등록(IoC관리)
@EnableWebSecurity //시큐리티 필터 추가 = 스프링 시큐리티를 해당 파일에서 설정한다.
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationFailureHandler customFailureHandler;

    // 해당 메서드의 리턴되는 오브젝트를 IoC로 등록해준다.
    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
//                .httpBasic().disable()
                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 기존것을 사용하지않는거... jwt같은 토큰방식에서사용...
//                .and()
                .cors()
                .and()
                .formLogin()
                .loginPage("/auth/loginForm")
                .loginProcessingUrl("/login") //login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행해준다.
                .failureHandler(customFailureHandler)
                .defaultSuccessUrl("/")
                .and()
                .authorizeRequests(authorize -> authorize.antMatchers("/user/**")
                        .access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                        .antMatchers("/item/**")
                        .access("hasRole('ROLE_ADMIN')")
                        .anyRequest().permitAll());
        return http.build();
    }
}
