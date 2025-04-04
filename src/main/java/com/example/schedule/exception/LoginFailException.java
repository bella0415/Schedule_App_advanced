package com.example.schedule.exception;

/**
 * 로그인 실패 시 발생하는 예외
 */

public class LoginFailException extends RuntimeException {
    public LoginFailException(String message) {
        super(message);
    }
}
