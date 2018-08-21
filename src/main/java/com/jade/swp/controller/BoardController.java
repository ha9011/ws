package com.jade.swp.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jade.swp.domain.Board;
import com.jade.swp.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	@Inject
	private BoardService service;

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET(Board board, Model model) throws Exception {
		logger.info("regist GET .....");
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(Board board, RedirectAttributes rttr) throws Exception {
		logger.info("regist POST ..... {}", board.toString());
		service.regist(board);

//		model.addAttribute("result", "success");
		rttr.addFlashAttribute("msg", "success");
//		return "/board/success";
		return "redirect:/board/listAll";
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(@RequestParam("bno") Integer bno, Model model) throws Exception {
		logger.info("read GET .....");
		Board board = service.read(bno);
		logger.info(">>>> board.read: {}", board);
		model.addAttribute(board);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public void updateGet(@RequestParam("bno") Integer bno, Model model) throws Exception {
		logger.info("update GET .....");
		Board board = service.read(bno);
		logger.info(">>>> board.update: {}", board);
		model.addAttribute(board);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updatePost(Board board, RedirectAttributes rttr) throws Exception {
		logger.info("update POST ..... {}", board.getBno());
		service.modify(board);
		rttr.addFlashAttribute("msg", "save-ok");
		return "redirect:/board/read?bno=" + board.getBno();
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String remove(@RequestParam("bno") Integer bno, RedirectAttributes rttr) throws Exception {
		logger.info("remove GET .....");
		service.remove(bno);
		rttr.addFlashAttribute("msg", "remove-ok");
		return "redirect:/board/listAll";
	}
	
	@RequestMapping(value="/listAll", method = RequestMethod.GET)
	public void listAll(Model model) throws Exception {
		logger.info(">>>> listAll");
		List<Board> boards = service.listAll();
		model.addAttribute("list", boards);
	}
	
	@RequestMapping(value = "/dummy10", method = RequestMethod.GET)
	public String dummy10(RedirectAttributes rttr) throws Exception {
		logger.info("dummy10 .....");
		service.dummy10();
		rttr.addFlashAttribute("msg", "dummy10-ok");
		return "redirect:/board/listAll";
	}

}
