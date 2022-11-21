package com.jitlee.shop.config.auth;

import com.jitlee.shop.entity.Member;
import com.jitlee.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 시큐리티 설정에서 loginProcessingUrl("/login");
// /login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어 있는 loadUserByUsername 함수가 실행
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username : " + username);
        Member memberEntity = memberRepository.findByUsername(username).orElseGet(()->{
            return null;
        });
        System.out.println(memberEntity);
        if(memberEntity != null) {
            System.out.println("good");
            return new PrincipalDetails(memberEntity);
        }
        return null;
    }
}