package com.example.schedule.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

/**
 * 로그인 요청 데이터를 담는 DTO 클래스
 *
 */

@Getter
public class LoginRequestDto {

    /**
     * 로그인 이메일
     */

    @Email(message = "올바른 이메일 형식이어야 합니다.")
    @NotBlank(message = "이메일은 필수입니다.")
    private String email;

    /**
     * 로그인 비밀번호
     */

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;
}

