package com.jade.swp;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jade.swp.domain.Board;
import com.jade.swp.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class BoardDAOTest {

	@Inject
	private BoardDAO dao;

	private static Integer maxbno = 0;
	private static boolean didUpdate = false;

	private static Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);

	@Before
	public void getMaxbno() throws Exception {
		logger.warn("BEFORE maxbno=" + maxbno);
		if (maxbno == 0) {
			maxbno = dao.getMaxbno();
			logger.info("GET maxbno={}", maxbno);
		}
	}

	@Test
	public void testCreate() throws Exception {
		Board board = dummyBoard("새글제목", "새글 내용");
		System.out.println(board);
		dao.create(board);
	}

	@Test
	public void testRead() throws Exception {
		Board first = dao.read(maxbno);
		logger.info(first != null ? first.toString() : "Board is empty!!!!!!!!!!");
	}

	@Test
	public void testUpdate() throws Exception {
		Board board = dummyBoard("수정제목", "수정 내용");
		board.setBno(maxbno);
		dao.update(board);
		didUpdate = true;
		
		logger.info(dao.read(maxbno).toString());
	}

	@After
	public void testDelete() throws Exception {
		if (didUpdate) {
			dao.delete(maxbno);
			didUpdate = false;
		}
	}

	@Test
	public void testListAll() throws Exception {
		dao.listAll();
	}

	private Board dummyBoard(String title, String content) {
		Board board = new Board();
		board.setTitle(title);
		board.setContent(content);
		board.setWriter("user00");
		return board;
	}
}
