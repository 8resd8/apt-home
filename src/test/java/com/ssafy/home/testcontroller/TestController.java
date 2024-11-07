package com.ssafy.home.testcontroller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

// 테스트에 사용할 컨트롤러
@RestController
public class TestController {

    @PostMapping("/broker/some-path")
    public String testBroker() {
        return "broker";
    }

    @PostMapping("/member/some-path")
    public String testMember() {
        return "member";
    }
}