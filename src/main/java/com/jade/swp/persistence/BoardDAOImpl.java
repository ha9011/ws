package com.jade.swp.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.jade.swp.domain.Board;
import com.jade.swp.domain.Criteria;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
	private SqlSession session;

	private static final String NS = "com.jade.swp.persistence.BoardMapper";
	private static final String CREATE = NS + ".create";
	private static final String READ = NS + ".read";
	private static final String UPDATE = NS + ".update";
	private static final String DELETE = NS + ".delete";
	private static final String SELECT_ALL = NS + ".listAll";
	private static final String SELECT_PAGE = NS + ".listPage";
	private static final String SELECT_CRITERIA = NS + ".listCriteria";
	private static final String GetMaxbno = NS + ".getMaxbno";
	private static final String COUNT_PAGING = NS + ".countPaging";

	@Override
	public void create(Board board) throws Exception {
		session.insert(CREATE, board);
	}

	@Override
	public Board read(Integer bno) throws Exception {
		return session.selectOne(READ, bno);
	}

	@Override
	public void update(Board board) throws Exception {
		session.update(UPDATE, board);
	}

	@Override
	public void delete(Integer bno) throws Exception {
		session.delete(DELETE, bno);
	}

	@Override
	public List<Board> listAll() throws Exception {
		return session.selectList(SELECT_ALL);
	}

	@Override
	public Integer getMaxbno() throws Exception {
		return session.selectOne(GetMaxbno);
	}

	@Override
	public List<Board> listPage(int page) throws Exception {
		if (page <= 0)
			page = 1;
		
		page = (page - 1) * 10;
		return session.selectList(SELECT_PAGE, page);
	}

	@Override
	public List<Board> listCriteria(Criteria criteria) throws Exception {
		return session.selectList(SELECT_CRITERIA, criteria);
	}

	@Override
	public int countPaging(Criteria criteria) {
		return session.selectOne(COUNT_PAGING, criteria);
	}

}
