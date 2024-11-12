package com.ssafy.home.profile.member.controller;

import com.ssafy.home.profile.member.dto.MemberProfileResponse;
import com.ssafy.home.profile.member.dto.MemberUpdateRequest;
import com.ssafy.home.profile.member.dto.PasswordChangeRequest;
import com.ssafy.home.profile.member.dto.PasswordResetRequest;
import com.ssafy.home.profile.member.service.MemberProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member/profile")
public class MemberProfileController {

    private final MemberProfileService memberService;


    @GetMapping("/{memberId}")
    public ResponseEntity<MemberProfileResponse> getMemberProfile(@PathVariable String memberId) {
        MemberProfileResponse profile = memberService.findMemberById(memberId);
        return ResponseEntity.ok(profile);
    }

    @PostMapping("/{memberId}")
    public ResponseEntity<Void> updateMemberProfile(@PathVariable String memberId, @RequestBody MemberUpdateRequest request) {
        memberService.updateMember(memberId, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable String memberId, @RequestParam String password) {
        memberService.deleteMember(memberId, password);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/password-reset")
    public ResponseEntity<Void> resetPassword(@RequestBody PasswordResetRequest request) {
        memberService.resetPassword(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/password-change")
    public ResponseEntity<Void> changePassword(@RequestBody PasswordChangeRequest request) {
        memberService.changePassword(request);
        return ResponseEntity.ok().build();
    }

}
