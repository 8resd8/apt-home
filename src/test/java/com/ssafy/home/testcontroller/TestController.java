package com.ssafy.home.testcontroller;

import com.ssafy.home.annotation.Login;
import com.ssafy.home.auth.domain.Broker;
import com.ssafy.home.auth.domain.Member;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 테스트에 사용할 컨트롤러
@RestController
public class TestController {

    @GetMapping("/member")
    public String getMember(@Login Member member) {
        return member != null ? "Member: " + member.getMid() : "No member";
    }

    @GetMapping("/broker")
    public String getBroker(@Login Broker broker) {
        return broker != null ? "Broker: " + broker.getBid() : "No broker";
    }

    @PostMapping("/broker/some-path")
    public String testBroker() {
        return "broker";
    }

    @PostMapping("/member/some-path")
    public String testMember() {
        return "member";
    }
}