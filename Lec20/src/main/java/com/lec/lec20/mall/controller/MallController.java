package com.lec.lec20.mall.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lec.lec20.mall.Mall;

@Controller //Controller���� ǥ�����ִ� ������̼�
@RequestMapping("/mall") //�ش� Ŭ������ ����� mapping �κ� ����
public class MallController {
	
	//ModelAttribute�� �̿��� Ŀ�ǵ� ��ü�� �ҷ����� �� �Բ� ȣ��ȴ�.
	@ModelAttribute("cp")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}
	
	//serverTime�̶�� �̸����� return ����  Ŀ�ǵ� ��ü�� �ҷ����� �Լ��� return �Ǵ� jsp���� ����� �� �ִ�.
	@ModelAttribute("serverTime")
	public String getServerTime(Locale locale) {
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		return dateFormat.format(date);
	}
	
	@RequestMapping("/index")
	public String mallIndex(Mall mall,  
			@CookieValue(value="gender", required=false) Cookie genderCookie, 
			HttpServletRequest request) {
	//@CookieValue�� ���� cookie�� ������ �� �ش� value�� ������ exception�� ���Ƿ� required=false�� �������༭ exception�� �����ش�!	
		
		if(genderCookie != null) { 
			mall.setGender(genderCookie.getValue());//��Ű�� �ִٸ� �ش� ��Ű�� ���� �����ͼ� mall�� �����Ѵ�.
			System.out.println("Cookie�� ����� gender ��: " + genderCookie.getValue());
		}
		else {
			System.out.println("Cookie�� �������� ����!");
		}
		
		if(request.getSession().getAttribute("gender") != null) { 
			mall.setGender((String)request.getSession().getAttribute("gender"));//������ �ִٸ� �ش� ���� �����ͼ� mall�� �����Ѵ�.
			System.out.println("Session�� ����� gender ��: " + request.getSession().getAttribute("gender"));
		}
		else {
			System.out.println("Session�� �������� ����!");
		}
		
		//Cookie �Ǵ� Session�� ����� ���� �ִٸ� �ش� ���� ���� index �ϴ��� �޽����� �����ش�.
		return "/mall/index";
	}
	
	@RequestMapping("/main")
	public String mallMain(Mall mall, HttpServletRequest request, HttpServletResponse response){
		
		Cookie genderCookie = new Cookie("gender", mall.getGender());
		//gender��� �̸����� mall.getGender()�� ���� ��Ű�� ����!
		HttpSession session = request.getSession();
		//request ��ü�κ��� ������ �����´�.
		
		if(mall.isCookieDel()) {
			genderCookie.setMaxAge(0);
		} else {
			genderCookie.setMaxAge(60*60*24*30); //��Ű�� �����ð��� �����ش�!(= ��Ű�� �����Ѵ�.) 
			System.out.println("Cookie�� ����!");
		}
		
		if(mall.isSessionDel()) {
			session.invalidate(); //������ �����Ѵ�.
		}else {
			//request�� ���� ������ �����ͼ� gender��� �̸����� ���� �־��ش�.
			session.setAttribute("gender", mall.getGender());
			System.out.println("Session�� ����!");
		}
		
		response.addCookie(genderCookie); //response�� ��Ű�� ��´�.(��Ű�� Ŭ���̾�Ʈ�� ������ �ִ� ��������)	
		
		return "/mall/main";
	}
	
}