package com.ssafy.home.profile.member.controller;

import com.ssafy.home.domain.Member;
import com.ssafy.home.global.annotation.Login;
import com.ssafy.home.profile.member.dto.*;
import com.ssafy.home.profile.member.service.MemberProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class MemberProfileController {

    private final MemberProfileService memberService;


    @GetMapping("/member/profile")
    public ResponseEntity<MemberResponse> getMemberProfile(@Login Member member) {
        MemberResponse profile = memberService.findMemberById(member);
        return ResponseEntity.ok(profile);
    }

    @PostMapping("/member/profile")
    public ResponseEntity<MemberResponse> updateMemberProfile(@Login Member member,
                                                              @Validated @RequestPart("member") MemberUpdateRequest request,
                                                              @RequestPart(value = "profileImage", required = false) MultipartFile image) {
        MemberResponse profile = memberService.updateMember(member, request, image);
        return ResponseEntity.ok(profile);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMember(@Login Member member, @Validated @RequestBody MemberDeleteRequest request) {
        memberService.deleteMember(member, request);
    }

    @PatchMapping("/profile/password-change")
    @ResponseStatus(HttpStatus.OK)
    public void changePassword(@Login Member member, @Validated @RequestBody PasswordChangeRequest request) {
        memberService.changePassword(member, request);
    }

    @PatchMapping("/profile/password-set")
    @ResponseStatus(HttpStatus.OK)
    public void setPassword(@Validated @RequestBody PasswordResetRequest request) {
        memberService.resetPassword(request);
    }
}
