package com.jade.swp.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jade.swp.domain.Board;

public class LoginInterceptor extends HandlerInterceptorAdapter implements SessionNames {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler)
			throws Exception {
		
		System.out.println("LoginInterceptor.pre>>");
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute(LOGIN) != null) {
			session.removeAttribute(LOGIN);
		}

		return true;
	}

	@Override
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response, 
			Object handler, ModelAndView modelAndView)
			throws Exception {
		
		
		HttpSession session = request.getSession();
		
		Object user = modelAndView.getModelMap().get("user");
		System.out.println("LoginInterceptor.post>>" + user);
		
		if (user != null) {
			session.setAttribute(LOGIN, user);
		}
	}
}
