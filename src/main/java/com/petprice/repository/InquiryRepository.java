package com.petprice.repository;

import com.petprice.entity.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
    List<Inquiry> findByEmail(String email); // 특정 사용자의 문의 조회
}
