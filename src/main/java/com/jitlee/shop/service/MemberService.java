package com.jitlee.shop.service;

import com.jitlee.shop.entity.Member;
import com.jitlee.shop.entity.RoleType;
import com.jitlee.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member find(String username) {
        Member member = memberRepository.findByUsername(username).orElseGet(()->{
            return null;
        });
        return member;
    }

    @Transactional
    public void join(Member member) {
        member.setRole(RoleType.USER);
        memberRepository.save(member);
    }
}
