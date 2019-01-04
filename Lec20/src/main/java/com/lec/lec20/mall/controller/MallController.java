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

@Controller //Controller임을 표시해주는 어노테이션
@RequestMapping("/mall") //해당 클래스의 공통된 mapping 부분 설정
public class MallController {
	
	//ModelAttribute를 이용한 커맨드 객체가 불러와질 때 함께 호출된다.
	@ModelAttribute("cp")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}
	
	//serverTime이라는 이름으로 return 값을  커맨드 객체가 불러와진 함수의 return 되는 jsp에서 사용할 수 있다.
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
	//@CookieValue를 통해 cookie를 가져올 때 해당 value가 없으면 exception이 나므로 required=false로 설정해줘서 exception을 막아준다!	
		
		if(genderCookie != null) { 
			mall.setGender(genderCookie.getValue());//쿠키가 있다면 해당 쿠키의 값을 가져와서 mall에 저장한다.
			System.out.println("Cookie에 저장된 gender 값: " + genderCookie.getValue());
		}
		else {
			System.out.println("Cookie가 생성되지 않음!");
		}
		
		if(request.getSession().getAttribute("gender") != null) { 
			mall.setGender((String)request.getSession().getAttribute("gender"));//세션이 있다면 해당 값을 가져와서 mall에 저장한다.
			System.out.println("Session에 저장된 gender 값: " + request.getSession().getAttribute("gender"));
		}
		else {
			System.out.println("Session이 생성되지 않음!");
		}
		
		//Cookie 또는 Session에 저장된 값이 있다면 해당 값을 통해 index 하단의 메시지를 보여준다.
		return "/mall/index";
	}
	
	@RequestMapping("/main")
	public String mallMain(Mall mall, HttpServletRequest request, HttpServletResponse response){
		
		Cookie genderCookie = new Cookie("gender", mall.getGender());
		//gender라는 이름으로 mall.getGender()의 값을 쿠키로 생성!
		HttpSession session = request.getSession();
		//request 객체로부터 세션을 가져온다.
		
		if(mall.isCookieDel()) {
			genderCookie.setMaxAge(0);
		} else {
			genderCookie.setMaxAge(60*60*24*30); //쿠키의 유지시간을 정해준다!(= 쿠키를 생성한다.) 
			System.out.println("Cookie를 생성!");
		}
		
		if(mall.isSessionDel()) {
			session.invalidate(); //세션을 삭제한다.
		}else {
			//request로 부터 세션을 가져와서 gender라는 이름으로 값을 넣어준다.
			session.setAttribute("gender", mall.getGender());
			System.out.println("Session을 생성!");
		}
		
		response.addCookie(genderCookie); //response에 쿠키를 담는다.(쿠키는 클라이언트가 가지고 있는 연결정보)	
		
		return "/mall/main";
	}
	
}