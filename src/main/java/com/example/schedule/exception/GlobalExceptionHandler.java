package com.example.schedule.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.List;

/**
 * 전역 예외 처리를 담당하는 핸들러 클래스
 *
 * 프로젝트 전반에서 발생하는 예외를 공통 포맷(JSON)으로 처리함
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 로그인 실패 예외를 처리하고, 401 상태 코드를 가진 JSON 응답을 반환
     *
     * @param ex LoginFailException 예외 객체
     * @param request 현재 HTTP 요청
     * @return JSON 형태의 에러 응답
     */

    @ExceptionHandler(LoginFailException.class)
    public ResponseEntity<Map<String, Object>> handleLoginFail(LoginFailException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                Map.of(
                        "timestamp", LocalDateTime.now(),
                        "status", 401,
                        "error", "UNAUTHORIZED",
                        "code", "LOGIN_FAIL",
                        "message", ex.getMessage(),
                        "path", request.getRequestURI()
                )
        );
    }

    /**
     * 유효성 검사 실패 예외를 처리하고, 400 상태 코드를 가진 JSON 응답을 반환
     *
     * @param ex MethodArgumentNotValidException 예외 객체
     * @param request 현재 HTTP 요청
     * @return JSON 형태의 에러 응답
     */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(
            MethodArgumentNotValidException ex, HttpServletRequest request) {

        List<Map<String, String>> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> Map.of(
                        "field", error.getField(),
                        "message", error.getDefaultMessage()
                )).toList();

        return ResponseEntity.badRequest().body(
                Map.of(
                        "timestamp", LocalDateTime.now(),
                        "status", 400,
                        "error", "BAD_REQUEST",
                        "code", "VALIDATION_ERROR",
                        "message", "잘못된 입력값입니다",
                        "path", request.getRequestURI(),
                        "fieldErrors", fieldErrors
                )
        );
    }

}
