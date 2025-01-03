package com.petprice.entity;

import com.petprice.constant.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = true)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = true)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = true)
    private String sido; // 시도 정보

    @Column(nullable = true)
    private String sigungu; // 시군구 정보

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public static User createGeneralUser(String email, String username, String password, String phoneNumber, String sido, String sigungu) {
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);
        user.setSido(sido);
        user.setSigungu(sigungu);
        user.setRole(Role.ADMIN);
        return user;
    }

    public static User createSocialUser(String email, String username) {
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setRole(Role.SOCIAL_USER);
        return user;
    }
}