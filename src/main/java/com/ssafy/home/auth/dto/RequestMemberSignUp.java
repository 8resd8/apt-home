package com.ssafy.home.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public record RequestMemberSignUp(
        @NotBlank(message = "아이디는 필수 항목입니다.")
        @Size(min = 5, max = 255, message = "아이디는 5자 이상 255자 이하로 입력해야 합니다.")
        String id,

        @NotBlank(message = "비밀번호는 필수 항목입니다.")
//        @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
        String password,

        @NotBlank(message = "이메일은 필수 항목입니다.")
        @Email(message = "유효한 이메일 주소를 입력해야 합니다.")
        String email,

        @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message = "전화번호는 000-0000-0000 형식이어야 합니다.")
        String phoneNum,

        @NotBlank(message = "이름은 필수 항목입니다.")
        @Size(max = 45, message = "이름은 최대 45자까지 입력할 수 있습니다.")
        String name
) { }