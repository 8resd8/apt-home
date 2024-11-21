package com.ssafy.home.profile.member.service;

import com.ssafy.home.auth.domain.Member;
import com.ssafy.home.auth.repository.MemberMapper;
import com.ssafy.home.auth.service.signup.StorageService;
import com.ssafy.home.profile.member.dto.*;
import com.ssafy.home.profile.member.exception.CannotUpdateMemberException;
import com.ssafy.home.profile.member.exception.NotFoundMemberException;
import com.ssafy.home.profile.member.exception.ValidPasswordException;
import com.ssafy.home.profile.member.repository.MemberProfileMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberProfileServiceImpl implements MemberProfileService {

    private final MemberMapper memberMapper;
    private final MemberProfileMapper memberProfileMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final StorageService storageService;
    private final HttpSession httpSession;

    @Override
    public MemberResponse findMemberById(Member member) {
        Optional<MemberResponse> findMember = memberProfileMapper.findMemberById(member.getMid());

        return getFindMember(findMember);
    }

    @Override
    public MemberResponse updateMember(Member member, MemberUpdateRequest updateRequest, MultipartFile image) {
        checkCurrentPassword(member, updateRequest);
        int isSuccess = memberProfileMapper.updateMemberProfile(member.getMid(), updateRequest, storageService.uploadFile(image));
        if (isSuccess == 0) {
            throw new CannotUpdateMemberException();
        }


        Optional<MemberResponse> findMember = memberProfileMapper.findMemberById(member.getMid());

        return getFindMember(findMember);
    }


    @Override
    public void deleteMember(Member member, MemberDeleteRequest request) {
        Optional<Member> optionalMember = memberMapper.findById(member.getMid());

        if (optionalMember.isEmpty()) {
            throw new NotFoundMemberException();
        }

        Member findMember = optionalMember.get();
        checkPassword(findMember.getPassword(), request.password(), findMember.getSalt());

        memberProfileMapper.deleteMemberProfile(member.getMid(), request.password());
        httpSession.invalidate();
    }

    @Override
    public void changePassword(Member member, PasswordChangeRequest passwordChangeRequest) {
        // 1. 입력한 기존 비밀번호는 저장된 번호와 일치해야 한다.
        checkCurrentPassword(member, passwordChangeRequest);

        // 2. 새 비밀번호는 일치해야한다.
        if (!passwordChangeRequest.newPassword1().equals(passwordChangeRequest.newPassword2())) {
            throw new ValidPasswordException("새로운 비밀번호가 서로 맞지 않습니다.");
        }

        // 3. 비밀번호 변경
        String newSalt = generateSalt();
        int updateCount = memberProfileMapper.updatePassword(member.getMid(), passwordChangeRequest.newPassword1(), newSalt);

        if (updateCount == 0) {
            throw new CannotUpdateMemberException("비밀번호 변경에 실패했습니다.");
        }
        httpSession.invalidate();
    }

    @Override
    public void resetPassword(PasswordResetRequest request) {
        if (!request.password().equals(request.confirmPassword())) {
            throw new ValidPasswordException("비밀번호가 일치하지 않습니다.");
        }
        String salt = generateSalt();
        String hashedPassword = hashPassword(request.confirmPassword(), salt);

        memberProfileMapper.updatePassword(request.memberId(), hashedPassword, salt);
    }

    private void checkCurrentPassword(Member member, MemberUpdateRequest request) {
        String originPwd = getOriginPwd(member);

        checkPassword(originPwd, request.password(), member.getSalt());
    }

    private void checkCurrentPassword(Member member, PasswordChangeRequest passwordChangeRequest) {
        String originPwd = getOriginPwd(member);

        checkPassword(originPwd, passwordChangeRequest.currentPassword(), member.getSalt());
    }

    private String getOriginPwd(Member member) {
        Optional<Member> optionalMember = memberMapper.findById(member.getMid());
        if (optionalMember.isEmpty()) {
            throw new NotFoundMemberException();
        }

        Member findMember = optionalMember.get();

        return findMember.getPassword();
    }

    private MemberResponse getFindMember(Optional<MemberResponse> findMember) {
        if (findMember.isEmpty()) {
            throw new NotFoundMemberException();
        }

        return findMember.get();
    }

    private void checkPassword(String hashedPassword, String requestPassword, String salt) {
        if (!passwordEncoder.matches(requestPassword + salt, hashedPassword)) {
            throw new ValidPasswordException();
        }
    }

    private String generateSalt() {
        return Long.toHexString(Double.doubleToLongBits(Math.random()));
    }

    private String hashPassword(String password, String salt) {
        return passwordEncoder.encode(password + salt);
    }
}
