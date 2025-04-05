package com.mall.dao;

import com.mall.model.Board;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface BoardDAO {
    List<Board> selectAllBoards();
    void insertBoard(Board board);
    Board selectBoardById(@Param("boardId") int boardId);
    void increaseViewCount(@Param("boardId") int boardId);
    void updateBoard(Board board);
    void deleteBoard(@Param("boardId") int boardId);
}
