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
@RequestMapping("/member") //���� �޼ҵ���� RequestMapping�� �ߺ��Ǵ� �κ��� class ��ü���� RequestMapping���� �������� 
public class MemberController {

	@Autowired
	MemberService service;
	
	//@ModelAttribute �� �̿��� Ŀ�ǵ� ��ü�� �ҷ����� �� �� �޼ҵ嵵 �����
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
		//ModelAndView ��ü�� �̿��ϸ� �����Ϳ� �並 �Բ� ������ �� ����
		Member[] members = service.memberModify(member);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("memBef", members[0]);
		mav.addObject("memAft", members[1]);
		
		mav.setViewName("memModifyOk");
		
		//return Ÿ���� String�� �ƴ϶� ModelAndView!
		return mav;
	}
}