package com.mall.service;

import com.mall.dao.CommentDAO;
import com.mall.model.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
	
	 	@Autowired
	    private CommentDAO commentDAO;

	 // 댓글 작성
	    public void createComment(Comment comment) {
	        commentDAO.insertComment(comment);
	    }

	    // 댓글 삭제
	    public void deleteComment(int commentId) {
	        commentDAO.deleteComment(commentId);
	    }

	    // 특정 대상에 대한 댓글 목록 조회
	    public List<Comment> getCommentsByTarget(int targetId, String targetType) {
	        return commentDAO.getCommentsByTarget(targetId, targetType);
	    }
	    
	    // ✅ 댓글 수정 (추가된 부분)
	    public void updateComment(int commentId, String content) {
	        commentDAO.updateComment(commentId, content);
	    }
	    
	 // 댓글 조회
	    public Comment getCommentById(int commentId) {
	        return commentDAO.getCommentById(commentId);  // 댓글 ID로 댓글 조회
	    }
	    
	}