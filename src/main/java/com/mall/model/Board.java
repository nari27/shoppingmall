package com.mall.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class Board {
    private int boardId;
    private String title;
    private String content;
    private String username;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private int viewCount;
    private List<Comment> commentList; 

    // Getter, Setter
}
