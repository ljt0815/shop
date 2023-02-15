package com.jitlee.shop.service;

import com.jitlee.shop.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
@Rollback(value = false)

class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    public void test() {
        Member member = new Member("jitlee", "1234", "jitlee@naver.com");

        //memberService.join(member);
        Member member1 = memberService.find("jitlee");
        System.out.println(member1.getEmail());
    }
}