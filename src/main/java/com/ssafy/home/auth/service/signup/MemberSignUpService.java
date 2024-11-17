package com.ssafy.home.auth.service.signup;

import com.ssafy.home.auth.domain.Member;
import com.ssafy.home.auth.dto.request.MemberSignUpRequest;
import com.ssafy.home.auth.dto.response.SignUpResponse;
import com.ssafy.home.auth.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class MemberSignUpService {

    private final MemberMapper memberMapper;
    private final SignUpHelper signUpHelper;
    private final StorageService storageService;

    public SignUpResponse signUp(MemberSignUpRequest request, MultipartFile profileImage) {
        String profileImageUrl = storageService.uploadFile(profileImage);

        signUpHelper.checkDuplicatedId(request.id());
        String salt = signUpHelper.generateSalt();
        String hashedPassword = signUpHelper.hashPassword(request.password(), salt);

        Member member = Member.builder()
                .password(hashedPassword)
                .mid(request.id())
                .email(request.email())
                .salt(salt)
                .name(request.name())
                .phoneNum(request.phoneNum())
                .profileImageUrl(profileImageUrl)
                .build();

        memberMapper.insertMember(member);

        return new SignUpResponse(request.id(), request.email());
    }

}
