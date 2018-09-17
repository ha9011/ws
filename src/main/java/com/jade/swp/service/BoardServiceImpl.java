package com.jade.swp.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.jade.swp.domain.Board;
import com.jade.swp.domain.Criteria;
import com.jade.swp.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO dao;

	@Override
	public void regist(Board board) throws Exception {
		dao.create(board);
	}

	@Override
	public Board read(Integer bno) throws Exception {
		return dao.read(bno);
	}

	@Override
	public void modify(Board board) throws Exception {
		dao.update(board);
	}

	@Override
	public void remove(Integer bno) throws Exception {
		dao.delete(bno);
	}

	@Override
	public List<Board> listAll() throws Exception {
		return dao.listAll();
	}

	@Override
	public void dummy10() throws Exception {
		Integer maxbno = dao.getMaxbno();
		for (int i = maxbno + 1; i <= maxbno + 10; i++) {
			Board board = new Board("title" + i, "content" + i, "writer" + i);
			dao.create(board);
		}
	}

	@Override
	public List<Board> listCriteria(Criteria criteria) throws Exception {
		return dao.listCriteria(criteria);
	}

	@Override
	public int countPaging(Criteria criteria) {
		return dao.countPaging(criteria);
	}

}
