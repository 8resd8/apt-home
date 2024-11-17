package com.ssafy.home.profile.member.controller;

import com.ssafy.home.auth.domain.Member;
import com.ssafy.home.global.annotation.Login;
import com.ssafy.home.profile.member.dto.*;
import com.ssafy.home.profile.member.service.MemberProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity<MemberResponse> updateMemberProfile(@Login Member member, @Validated @RequestBody MemberUpdateRequest request) {
        MemberResponse profile = memberService.updateMember(member, request);
        return ResponseEntity.ok(profile);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMember(@Login Member member, @Validated @RequestBody MemberDeleteRequest request) {
        memberService.deleteMember(member, request);
    }

    @PatchMapping("/password-change")
    @ResponseStatus(HttpStatus.OK)
    public void changePassword(@Login Member member, @Validated @RequestBody PasswordChangeRequest request) {
        memberService.changePassword(member, request);
    }

    @PatchMapping("/password-set")
    @ResponseStatus(HttpStatus.OK)
    public void setPassword(@Validated @RequestBody PasswordResetRequest request) {
        memberService.resetPassword(request);
    }
}
