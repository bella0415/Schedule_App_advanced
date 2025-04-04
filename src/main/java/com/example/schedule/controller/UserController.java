package com.example.schedule.controller;

import com.example.schedule.domain.entity.User;
import com.example.schedule.dto.UserRequestDto;
import com.example.schedule.dto.LoginRequestDto;
import com.example.schedule.dto.UserResponseDto;
import com.example.schedule.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 유저 관련 HTTP 요청을 처리하는 컨트롤러
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    /**
     * 회원가입
     */

    @PostMapping("/signup")
    public String signup(@RequestBody @Valid UserRequestDto requestDto) {
        userService.signup(requestDto);
        return "회원가입 성공";
    }

    /**
     * 유저 전체 조회
     */

    @GetMapping
    public List<UserResponseDto> findAll() {
        return userService.findAll();
    }

    /**
     * 유저 단건 조회
     */

    @GetMapping("/{id}")
    public UserResponseDto findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    /**
     * 유저 수정
     */

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @RequestBody @Valid UserRequestDto requestDto) {
        userService.update(id, requestDto);
        return "유저 정보 수정 완료";
    }

    /**
     * 유저 삭제
     */

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        userService.delete(id);
        return "유저 삭제 완료";
    }

    /**
     * 로그인 요청 처리
     *
     * @param requestDto 로그인 요청 정보 (이메일, 비밀번호)
     * @param request HTTP 요청 객체
     * @return 로그인 성공 메시지
     */

    @PostMapping("/login")
    public String login(@RequestBody @Valid LoginRequestDto requestDto, HttpServletRequest request) {
        User user = userService.login(requestDto.getEmail(), requestDto.getPassword());

        HttpSession session = request.getSession();
        session.setAttribute("loginUser", user.getId());

        return "로그인 성공";
    }


}


