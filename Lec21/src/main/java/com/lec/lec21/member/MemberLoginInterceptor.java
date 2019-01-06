package com.lec.lec21.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MemberLoginInterceptor extends HandlerInterceptorAdapter{

	//Controller���� �۾��� �����ϱ� ���� ����Ǵ� �޼ҵ� - return���� true�� ��쿡�� Controller�� ��û�� �Ѿ�� false�� ��� �Ѿ�� ���� 
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		HttpSession session = request.getSession(false);

		if(session != null) { 
			Object obj = session.getAttribute("member");
			if(obj != null) 
				return true; //���ǿ� member ���� �����ϹǷ� ���� ���� Controller���� ���� �۾��� ������ �� ����
		}
		
		//������ �������� �ʰų�(or ���ǿ� member���� �������� ���� ���) ���ͼ�Ʈ�Ͽ� root page�� �����̷�Ʈ!
		response.sendRedirect(request.getContextPath() + "/");
		
		return false; //false�� ��쿡�� Controller�� �������� ����
	}
	
	//Controller���� �۾� ���� �� ����Ǵ� �޼ҵ�
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		super.postHandle(request, response, handler, modelAndView);
	}
	
	//View�� ���� �۾����� ���� �� ����Ǵ� �޼ҵ�
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		super.afterCompletion(request, response, handler, ex);
	}
	

}
