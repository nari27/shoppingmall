package com.mall.controller;

import com.mall.model.Comment;
import com.mall.service.CommentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class CommentController {

    @Autowired
    private CommentService commentService;
	private Comment comment;

    // 댓글 작성 처리
    @PostMapping("/comment/create")
    public String createComment(@RequestParam("targetType") String targetType,
                                @RequestParam("targetId") int targetId,
                                @RequestParam("content") String content,
                                @SessionAttribute ("username") String username,  // 댓글등록 시 로그인된 유저네임 뜨게 하기
                                @SessionAttribute("userId") int userId) {
    	 if (username == null) {
    	        // username이 null이면 에러 처리 또는 리디렉션
    	        return "redirect:/errorPage";
    	    }// 세션에서 userId를 받아옴
    	
        Comment comment = new Comment(0, targetType, targetId, userId, username, content, null, null); // 댓글등록 시 로그인된 유저네임 뜨게 하기
        commentService.createComment(comment);
        return "redirect:/board/" + targetId;
    }
   
    
    // 댓글 삭제 처리
    @GetMapping("/comment/{targetId}/delete/{commentId}")
    public String deleteComment(@PathVariable int targetId, @PathVariable int commentId,
                                @SessionAttribute("username") String username) {
        Comment comment = commentService.getCommentById(commentId);
        if (comment == null) {
            // 댓글을 찾을 수 없으면 에러 페이지로 리디렉션
            return "redirect:/errorPage"; 
        }

        System.out.println("✅ 세션 username: " + username);
        System.out.println("✅ 댓글 작성자: " + comment.getUsername());
        
        // 작성자만 삭제 가능하도록 제한
        if (comment.getUsername() != null && comment.getUsername().equals(username)) {
            commentService.deleteComment(commentId);
        }

        return "redirect:/board/" + targetId;  // 삭제 후 게시글 페이지로 리디렉션
    }
    
 // 댓글 수정 처리
    @PostMapping("/comment/update")
    public String updateComment(@RequestParam("commentId") int commentId,
                                @RequestParam("targetId") int targetId,
                                @RequestParam("content") String content,
                                @SessionAttribute("username") String username) {
        Comment comment = commentService.getCommentById(commentId);
        System.out.println("현재 세션 username값 :" + username);
        // 댓글이 존재하는지 확인
        if (comment == null) {
            return "redirect:/errorPage";  // 댓글을 찾을 수 없으면 에러 페이지로 리디렉션
        }

        // 작성자만 수정 가능하도록 제한 (null 체크 추가)
        if (comment.getUsername() != null && comment.getUsername().equals(username)) {
            commentService.updateComment(commentId, content);
        } else {
            // 작성자가 아니면 수정할 수 없도록 처리
            return "redirect:/errorPage";
        }

        return "redirect:/board/" + targetId;  // 수정 후 게시글 페이지로 리디렉션
    }



}