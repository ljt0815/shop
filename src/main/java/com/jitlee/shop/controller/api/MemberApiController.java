package com.jitlee.shop.controller.api;

import com.jitlee.shop.dto.ResponseDto;
import com.jitlee.shop.entity.Member;
import com.jitlee.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/auth/idDupChk")
    public ResponseDto<Integer> idDupChk(@RequestBody Member member){
        Member findMember = memberService.find(member.getUsername());
        if (findMember == null)
            return new ResponseDto<>(HttpStatus.OK.value(), 1);
        return new ResponseDto<>(HttpStatus.OK.value(), -1);
    }

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody Member member) {
        memberService.join(member);
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }
}
