package com.petprice.service;

import com.petprice.dto.UserDto;
import com.petprice.entity.User;
import com.petprice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 일반 회원가입
    public User registerGeneralUser(UserDto userDto) {
        User user = User.createGeneralUser(
                userDto.getEmail(),
                userDto.getName(),
                passwordEncoder.encode(userDto.getPassword()),
                userDto.getPhoneNumber()
        );
        return userRepository.save(user);
    }

    // 이메일로 사용자 조회
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    // 로그인 검증
    public boolean validateUserLogin(String email, String rawPassword) {
        User user = getUserByEmail(email);
        return user != null && passwordEncoder.matches(rawPassword, user.getPassword());
    }
}