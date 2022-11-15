package com.jitlee.shop.controller.api;

import com.jitlee.shop.dto.ResponseDto;
import com.jitlee.shop.entity.Member;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberApiController {

    @PostMapping("/auth/idDupChk")
    public ResponseDto<Integer> idDupChk(@RequestBody Member member){
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
