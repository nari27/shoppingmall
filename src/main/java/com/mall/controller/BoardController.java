package com.mall.controller;

import com.mall.model.Board;
import com.mall.service.BoardService;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;
    // 게시판 목록 페이지
    @GetMapping("/api/board")
    @ResponseBody
    public List<Board> getBoardList() {
        return boardService.getAllBoards(); // 게시글 목록 반환
    }
    
    
    // 게시판 상세 조회 (조회수 증가 반영)
    @GetMapping("/board/{boardId}")
    public String boardDetail(@PathVariable int boardId,
    						  @SessionAttribute(value = "username", required = false) String username,  // 세션에서 username을 가져오되 없을 경우 예외가 발생하지 않음
							  Model model) {
        Board board = boardService.getBoardDetail(boardId);
        model.addAttribute("board", board);
        model.addAttribute("username", username);  // 댓글등록 시 로그인된 유저네임 뜨게 하기
        return "boardDetail";
    }
    
    
    @GetMapping("/board")
    public String getBoardList(Model model) {
        List<Board> boardList = boardService.getAllBoards();
        model.addAttribute("boardList", boardList);
        return "board";
    }
    
    // 게시판 작성 페이지
    @GetMapping("/write")
    public String writeBoardForm() {
        return "write";
    }

    // 게시판 작성 처리
    @PostMapping("/write")
    public String writeBoard(@ModelAttribute Board board, HttpSession session) {
        String username = (String) session.getAttribute("username"); // 세션에서 username 가져오기
        if (username == null) {
            return "redirect:/login"; // 로그인되지 않았다면 로그인 페이지로 리디렉트
        }
        board.setUsername(username); // Board 객체에 작성자 정보 저장
        boardService.createBoard(board);
        return "redirect:/board";
    }
    
    
    // 게시판 수정 페이지
    @GetMapping("/board/edit/{boardId}")
    public String editBoardForm(@PathVariable int boardId,
    							@SessionAttribute(value = "username", required = false) String username, // 세션에서 로그인 사용자 확인
                                Model model) {
        Board board = boardService.getBoardById(boardId);

     // 로그인한 사용자가 작성자가 아니면 접근 차단
        if (username == null || !board.getUsername().equals(username)) {
            return "redirect:/board"; // 게시판 목록으로 리디렉트
        }

        model.addAttribute("board", board);
        return "editBoard";
    }


    // 게시판 수정 처리
    @PostMapping("/board/{boardId}/edit")
    public String editBoard(@PathVariable int boardId,
                            @ModelAttribute Board board,
                            @SessionAttribute(value = "username", required = false) String username) {
        Board existingBoard = boardService.getBoardById(boardId);

     // 작성자만 수정 가능하도록 제한
        if (username == null || !existingBoard.getUsername().equals(username)) {
            return "redirect:/board"; // 권한 없으면 목록으로 이동
        }

        board.setBoardId(boardId);
        boardService.updateBoard(board);
        return "redirect:/board/" + boardId;
    }

    // 게시판 삭제 처리 (POST 요청 사용)
    @PostMapping("/board/delete")
    public String deleteBoard(@RequestParam("boardId") int boardId,
    		@SessionAttribute(value = "username", required = false) String username) {
        Board board = boardService.getBoardById(boardId);

     // 작성자만 삭제 가능하도록 제한
        if (username == null || !board.getUsername().equals(username)) {
            return "redirect:/board"; // 권한 없으면 목록으로 이동
        }

        boardService.deleteBoard(boardId);
        return "redirect:/board";
    }
}
