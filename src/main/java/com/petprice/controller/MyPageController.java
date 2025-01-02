package com.petprice.controller;

import com.petprice.dto.CommentDTO;
import com.petprice.dto.FreeBoardDTO;
import com.petprice.entity.User;
import com.petprice.service.FreeBoardService;
import com.petprice.service.InquiryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mypage")
public class MyPageController {

    private final FreeBoardService freeBoardService;

    public MyPageController(FreeBoardService freeBoardService) {
        this.freeBoardService = freeBoardService;
    }

    // 마이페이지 기본 데이터
    @GetMapping
    public ResponseEntity<?> getMyPage(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(403).body("권한이 없습니다. 로그인이 필요합니다.");
        }

        return ResponseEntity.ok("마이페이지 데이터");
    }

    // 내가 작성한 글 가져오기
    @GetMapping("/myposts")
    public ResponseEntity<List<FreeBoardDTO>> getMyPosts(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(403).body(null);
        }
        return ResponseEntity.ok(freeBoardService.getPostsByUserId(user.getId()));
    }

    // 내가 댓글 단 글 가져오기
    @GetMapping("/mycomments")
    public ResponseEntity<List<CommentDTO>> getMyComments(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(403).body(null);
        }
        return ResponseEntity.ok(freeBoardService.getCommentsByUserId(user.getId()));
    }

    // 좋아요 표시한 글 가져오기
    @GetMapping("/likedposts")
    public ResponseEntity<List<FreeBoardDTO>> getLikedPosts(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(403).body(null);
        }
        return ResponseEntity.ok(freeBoardService.getLikedPostsByUserId(user.getId()));
    }

}
