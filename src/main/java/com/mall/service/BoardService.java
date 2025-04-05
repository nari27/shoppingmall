package com.mall.service;

import com.mall.dao.BoardDAO;
import com.mall.dao.CommentDAO;
import com.mall.model.Board;
import com.mall.model.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service

public class BoardService {
    private final BoardDAO boardDAO;
    private final CommentDAO commentDAO;

    @Autowired
    public BoardService(BoardDAO boardDAO, CommentDAO commentDAO) {
        this.boardDAO = boardDAO;
        this.commentDAO = commentDAO;
    }

    public List<Board> getAllBoards() {
        return boardDAO.selectAllBoards();
    }

    public void createBoard(Board board) {
        board.setCreatedDate(LocalDateTime.now());
        board.setUpdatedDate(null); // 새 글 작성 시 수정일은 NULL로 설정
        boardDAO.insertBoard(board);
    }
    
    public Board getBoardById(int boardId) {
        return boardDAO.selectBoardById(boardId);
    }

    // 게시글 수정 메서드
    public void updateBoard(Board board) {
        Board existingBoard = boardDAO.selectBoardById(board.getBoardId());
        if (existingBoard != null) { // 기존 글이 존재할 때만 수정 가능
            board.setUpdatedDate(LocalDateTime.now()); // 수동으로 수정일 설정
            boardDAO.updateBoard(board);
        }
    }
    public void deleteBoard(int boardId) {
        boardDAO.deleteBoard(boardId);
    }
    
    public Board getBoardDetail(int boardId) {
    	// 조회수 증가
        boardDAO.increaseViewCount(boardId);
        
        // 게시판 상세 정보 조회
        Board board = boardDAO.selectBoardById(boardId);
        
        // 해당 게시글에 대한 댓글 목록 조회
        List<Comment> commentList = commentDAO.getCommentsByTarget(boardId, "BOARD");
        
        // 댓글 목록을 Board 객체에 설정
        board.setCommentList(commentList);
        
        // 반환
        return board;
    }
}
