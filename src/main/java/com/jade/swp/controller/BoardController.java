package com.jade.swp.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping(value="/listAll", method = RequestMethod.GET)
	public void listAll(Model model) throws Exception {
		logger.info(">>>> listAll");
		List<Board> boards = service.listAll();
		model.addAttribute("list", boards);
	}

}
