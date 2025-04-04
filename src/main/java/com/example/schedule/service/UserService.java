package com.example.schedule.service;

import com.example.schedule.exception.LoginFailException;
import com.example.schedule.domain.entity.User;
import com.example.schedule.domain.repository.UserRepository;
import com.example.schedule.dto.UserRequestDto;
import com.example.schedule.dto.UserResponseDto;
import com.example.schedule.config.PasswordEncoder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 유저 관련 비즈니스 로직 처리 서비스
 */

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입 처리 메서드
     *
     * @param requestDto 유저 생성 요청 정보
     */

    public void signup(UserRequestDto requestDto) {
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

        User user = new User(
                requestDto.getUsername(),
                requestDto.getEmail(),
                encodedPassword  // 🔐 암호화된 비밀번호 저장!
        );

        userRepository.save(user);

        // 로그인 등 기존 메서드는 그대로 유지
    }

    /**
     * 유저 전체 조회
     */

    public List<UserResponseDto> findAll() {
        return userRepository.findAll().stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }

    /**
     * 유저 단건 조회
     */

    public UserResponseDto findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
        return new UserResponseDto(user);
    }

    /**
     * 유저 수정
     */
    @Transactional
    public void update(Long id, UserRequestDto requestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
        user.update(requestDto.getUsername(), requestDto.getEmail());
    }

    /**
     * 유저 삭제
     */
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * 유저 로그인 처리 메서드
     *
     * @param email 이메일
     * @param password 비밀번호
     * @return 로그인된 유저 객체
     * @throws LoginFailException 이메일 또는 비밀번호 불일치 시 예외 발생
     */

    public User login(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElseThrow(() -> new LoginFailException("이메일 또는 비밀번호가 일치하지 않습니다."));
    }

    }