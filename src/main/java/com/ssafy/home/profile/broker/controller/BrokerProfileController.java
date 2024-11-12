package com.ssafy.home.profile.broker.controller;

import com.ssafy.home.profile.member.service.MemberProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/broker/profile")
public class BrokerProfileController {

    private final MemberProfileService memberService;


//    @GetMapping("/{brokerId}")
//    public ResponseEntity<MemberProfileResponse> getMemberProfile(@PathVariable String brokerId) {
//        MemberProfileResponse profile = memberService.findMemberById(brokerId);
//        return ResponseEntity.ok(profile);
//    }
//
//    @PostMapping("/{brokerId}")
//    public ResponseEntity<Void> updateMemberProfile(@PathVariable String brokerId, @RequestBody MemberUpdateRequest request) {
//        memberService.updateMember(brokerId, request);
//        return ResponseEntity.ok().build();
//    }
//
//    @DeleteMapping("/{brokerId}")
//    public ResponseEntity<Void> deleteMember(@PathVariable String brokerId, @RequestParam String password) {
//        memberService.deleteMember(brokerId, password);
//        return ResponseEntity.noContent().build();
//    }
//
//    @PostMapping("/password-reset")
//    public ResponseEntity<Void> resetPassword(@RequestBody PasswordResetRequest request) {
//        memberService.resetPassword(request);
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping("/password-change")
//    public ResponseEntity<Void> changePassword(@RequestBody PasswordChangeRequest request) {
//        memberService.changePassword(request);
//        return ResponseEntity.ok().build();
//    }

}
