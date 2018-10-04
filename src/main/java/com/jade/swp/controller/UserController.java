package com.jade.swp.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jade.swp.domain.User;
import com.jade.swp.dto.LoginDTO;
import com.jade.swp.service.UserService;

@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Inject
	private UserService service;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void login() throws Exception {
		logger.info("login GET .....");
	}
	
	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public void loginPost(LoginDTO dto, Model model) throws Exception {
		logger.info("loginPost...LoginDTO={}", dto); 
		
		try {
			User user = service.login(dto);
			if (user != null) { // login fail
				model.addAttribute("user", user);
				
			} else {
				model.addAttribute("loginResult", "Login Fail!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
