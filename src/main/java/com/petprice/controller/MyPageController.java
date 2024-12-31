package com.petprice.controller;

import com.petprice.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mypage")
public class MyPageController {

    @GetMapping
    public ResponseEntity<?> getMyPage(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(403).body("권한이 없습니다. 로그인이 필요합니다.");
        }

        return ResponseEntity.ok("마이페이지 데이터");
    }
}
