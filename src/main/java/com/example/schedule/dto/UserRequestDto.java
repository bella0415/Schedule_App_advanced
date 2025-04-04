package com.example.schedule.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

/**
 * 유저 생성/수정 요청 데이터를 담는 DTO
 */

@Getter
public class UserRequestDto {

    @NotBlank(message = "유저명은 필수입니다.")
    @Size(max = 4, message = "유저명은 4자 이내여야 합니다.")
    private String username;

    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "올바른 이메일 형식이어야 합니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 4, message = "비밀번호는 4자 이상이어야 합니다.")
    private String password;
}

