package com.petprice.controller;

import com.petprice.dto.UserDto;
import com.petprice.entity.User;
import com.petprice.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        try {
            User user = userService.registerGeneralUser(userDto);
            return ResponseEntity.ok("회원가입 성공: " + user.getEmail());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("회원가입 실패: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto loginDto, HttpSession session) {
        if (userService.validateUserLogin(loginDto.getEmail(), loginDto.getPassword())) {
            User user = userService.getUserByEmail(loginDto.getEmail());

            // 사용자 정의 세션에 사용자 정보 저장
            session.setAttribute("user", user);

            // Spring Security 인증 정보 생성
            String role = "ROLE_" + user.getRole().name(); // 권한 설정
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    user.getEmail(),
                    null,
                    Collections.singletonList(new SimpleGrantedAuthority(role))
            );

            // SecurityContextHolder에 인증 정보 설정
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            // SecurityContext를 세션에 동기화
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

            return ResponseEntity.ok("로그인 성공: " + user.getEmail() + ", 권한: " + role);
        }

        return ResponseEntity.status(401).body("로그인 실패: 이메일 또는 비밀번호가 잘못되었습니다.");
    }

    // 유저 정보를 받기 위한 세션
    @GetMapping("/session")
    public ResponseEntity<?> getSession(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(401).body("로그인 필요: 세션에 사용자 정보가 없습니다.");
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        // Spring Security 인증 정보 제거
        SecurityContextHolder.clearContext();

        // 사용자 정의 세션 무효화
        session.invalidate();

        return ResponseEntity.ok("로그아웃 성공");
    }
}