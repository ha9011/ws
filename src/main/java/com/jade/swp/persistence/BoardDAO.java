package com.jade.swp.persistence;

import java.util.List;

import com.jade.swp.domain.Board;

public interface BoardDAO {
	void create(Board board) throws Exception;

	Board read(Integer bno) throws Exception;

	void update(Board board) throws Exception;

	void delete(Integer bno) throws Exception;

	List<Board> listAll() throws Exception;

	Integer getMaxbno() throws Exception;
}
