package com.ssafy.home.profile.member.controller;

import com.ssafy.home.auth.domain.Member;
import com.ssafy.home.global.annotation.Login;
import com.ssafy.home.profile.member.dto.MemberResponse;
import com.ssafy.home.profile.member.dto.MemberUpdateRequest;
import com.ssafy.home.profile.member.dto.PasswordChangeRequest;
import com.ssafy.home.profile.member.dto.PasswordResetRequest;
import com.ssafy.home.profile.member.service.MemberProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member/profile")
public class MemberProfileController {

    private final MemberProfileService memberService;


    @GetMapping
    public ResponseEntity<MemberResponse> getMemberProfile(@Login Member member) {
        MemberResponse profile = memberService.findMemberById(member);
        return ResponseEntity.ok(profile);
    }

    @PostMapping
    public ResponseEntity<MemberResponse> updateMemberProfile(@Login Member member, @RequestBody MemberUpdateRequest request) {
        MemberResponse profile = memberService.updateMember(member, request);
        return ResponseEntity.ok(profile);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMember(@Login Member member, @RequestParam String password) {
        memberService.deleteMember(member, password);
    }

    @PostMapping("/password-reset")
    @ResponseStatus(HttpStatus.OK)
    public void resetPassword(@RequestBody PasswordResetRequest request) {
        memberService.resetPassword(request);
    }

    @PostMapping("/password-change")
    @ResponseStatus(HttpStatus.OK)
    public void changePassword(@RequestBody PasswordChangeRequest request) {
        memberService.changePassword(request);
    }

}
