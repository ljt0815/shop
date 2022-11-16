package com.jitlee.shop.controller;

import com.jitlee.shop.dto.ResponseDto;
import com.jitlee.shop.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class MemberController {

    @GetMapping("/auth/loginForm")
    public String loginForm() {
        return "loginForm";
    }

    @GetMapping("/auth/joinForm")
    public String joinForm() {
        return "joinForm";
    }
}
