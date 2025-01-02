package com.petprice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentDTO {
    private Long id;
    private String content;
    private String authorUsername;
}
