package com.lec.lec19.member.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lec.lec19.member.Member;
import com.lec.lec19.member.service.MemberService;

@Controller
@RequestMapping("/member") //내부 메소드들의 RequestMapping의 중복되는 부분을 class 자체에서 RequestMapping으로 설정해줌 
public class MemberController {

	@Autowired
	MemberService service;
	
	//@ModelAttribute 를 이용한 커맨드 객체가 불려와질 때 이 메소드도 실행됨
	@ModelAttribute("serverTime")
	public String getServerTime(Locale locale) {
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		return dateFormat.format(date);
	}

	@RequestMapping(value = "/memLogin", method = RequestMethod.POST)
	public String memLogin(Member member) {
		
		service.memberSearch(member);
		
		return "memLoginOk";
	}
	
	@RequestMapping(value="/memJoin", method=RequestMethod.POST)
	public String memJoin(Member member) {
		
		service.memberRegister(member);
		
		return "memJoinOk";
	}
	
	@RequestMapping(value = "/memRemove", method = RequestMethod.POST)
	public String memRemove(@ModelAttribute("mem") Member member) {
		
		service.memberRemove(member);
		
		return "memRemoveOk";
	}
	
	@RequestMapping(value = "/memModify", method = RequestMethod.POST)
	public ModelAndView memModify(Member member) {
		//ModelAndView 객체를 이용하면 데이터와 뷰를 함께 전달할 수 있음
		Member[] members = service.memberModify(member);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("memBef", members[0]);
		mav.addObject("memAft", members[1]);
		
		mav.setViewName("memModifyOk");
		
		//return 타입이 String이 아니라 ModelAndView!
		return mav;
	}
}