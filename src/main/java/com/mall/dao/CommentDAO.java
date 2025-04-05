package com.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.mall.model.Comment;
@Mapper
public interface CommentDAO {
    // 댓글 작성
    void insertComment(Comment comment);

    // 댓글 삭제
    void deleteComment(int commentId);

    // 특정 대상에 대한 댓글 목록 조회
    List<Comment> getCommentsByTarget(int targetId, String targetType);
    
    // ✅ 댓글 수정 메서드 추가
    void updateComment(@Param("commentId") int commentId, @Param("content") String content);
    
 // 댓글 조회 (추가된 부분)
    @Select("SELECT * FROM comment WHERE comment_id = #{commentId}")
    Comment getCommentById(int commentId);  // 댓글 ID로 댓글 조회
}   