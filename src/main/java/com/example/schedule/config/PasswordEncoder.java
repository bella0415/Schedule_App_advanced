package com.example.schedule.config;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

/**
 * 비밀번호 암호화를 처리하는 컴포넌트
 */

@Component
public class PasswordEncoder {

    /**
     * 평문 비밀번호를 암호화
     *
     * @param rawPassword 평문 비밀번호
     * @return 암호화된 비밀번호
     */

    public String encode(String rawPassword) {
        return BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, rawPassword.toCharArray());
    }

    /**
     * 입력된 비밀번호와 암호화된 비밀번호 일치여부 확인
     *
     * @param rawPassword 평문 비밀번호
     * @param encodedPassword 암호화된 비밀번호
     * @return 일치 여부
     */

    public boolean matches(String rawPassword, String encodedPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.toCharArray(), encodedPassword);
        return result.verified;
    }
}
