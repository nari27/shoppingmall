package com.mall.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor  // 모든 필드를 받는 생성자 추가
@NoArgsConstructor
public class Comment {
    private int commentId;
    private String targetType;  // 'PRODUCT', 'BOARD', 'INQUIRY'
    private int targetId;       // 대상 ID (상품 ID, 게시글 ID, 문의글 ID 등)
    private int userId;    
    private String username;     // 추가 - 댓글등록 시 로그인된 유저네임 뜨게 하기
    private String content;     // 댓글 내용
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}