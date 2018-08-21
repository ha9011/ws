package com.jade.swp.service;

import java.util.List;

import com.jade.swp.domain.Board;

public interface BoardService {
	void regist(Board board) throws Exception;

	Board read(Integer bno) throws Exception;

	void modify(Board board) throws Exception;

	void remove(Integer bno) throws Exception;

	List<Board> listAll() throws Exception;

	void dummy10() throws Exception;
}
