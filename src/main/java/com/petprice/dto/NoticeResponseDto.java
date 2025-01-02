package com.petprice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class NoticeResponseDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdTime;
    private LocalDateTime upDateTime;
}