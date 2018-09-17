package com.jade.swp.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.jade.swp.domain.Criteria;
import com.jade.swp.domain.Reply;
import com.jade.swp.persistence.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Inject
	private ReplyDAO dao;

	@Override
	public List<Reply> listReply(Integer bno) throws Exception {
		return dao.list(bno);
	}

	@Override
	public void addReply(Reply reply) throws Exception {
		dao.create(reply);
	}

	@Override
	public void modifyReply(Reply reply) throws Exception {
		dao.update(reply);
	}

	@Override
	public void removeReply(Integer rno) throws Exception {
		dao.delete(rno);
	}

	@Override
	public List<Reply> listReplyPage(Integer bno, Criteria criteria) throws Exception {
		return dao.listPage(bno, criteria);
	}

	@Override
	public int count(Integer bno) throws Exception {
		return dao.count(bno);
	}

	@Override
	public Reply readReply(Integer rno) {
		return dao.read(rno);
	}

}
