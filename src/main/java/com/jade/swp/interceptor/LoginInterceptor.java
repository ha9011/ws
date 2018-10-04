package com.jade.swp.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

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
			Object handler, ModelAndView modelAndView) throws Exception {
		
		HandlerMethod method = (HandlerMethod) handler;
		System.out.println("MMMM>> Bean: " + method.getBean() + ", Method: " + method.getMethod());
		System.out.println("MMMM>> Model: " + modelAndView);
		
		HttpSession session = request.getSession();
		
		Object user = modelAndView.getModelMap().get("user");
		System.out.println("LoginInterceptor.post>>" + user);
		
		if (user != null) {
			session.setAttribute(LOGIN, user);
			
			Cookie loginCookie = new Cookie("loginCookie", session.getId());
			loginCookie.setPath("/");
			loginCookie.setMaxAge(7 * 24 * 60 * 60);
			
			response.addCookie(loginCookie);
		}
	}
}
