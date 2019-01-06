package com.lec.lec21.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MemberLoginInterceptor extends HandlerInterceptorAdapter{

	//Controller에서 작업을 수행하기 전에 실행되는 메소드 - return값이 true일 경우에는 Controller로 요청이 넘어가고 false일 경우 넘어가지 않음 
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		HttpSession session = request.getSession(false);

		if(session != null) { 
			Object obj = session.getAttribute("member");
			if(obj != null) 
				return true; //세션에 member 값이 존재하므로 문제 없이 Controller에서 이후 작업을 수행할 수 있음
		}
		
		//세션이 존재하지 않거나(or 세션에 member값이 존재하지 않을 경우) 인터센트하여 root page로 리다이렉트!
		response.sendRedirect(request.getContextPath() + "/");
		
		return false; //false일 경우에는 Controller를 수행하지 않음
	}
	
	//Controller에서 작업 수행 후 실행되는 메소드
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		super.postHandle(request, response, handler, modelAndView);
	}
	
	//View에 대한 작업까지 끝난 후 실행되는 메소드
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		super.afterCompletion(request, response, handler, ex);
	}
	

}
