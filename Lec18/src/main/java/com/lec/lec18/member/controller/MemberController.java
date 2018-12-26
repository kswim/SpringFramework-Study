package com.lec.lec18.member.controller;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lec.lec18.member.Member;
import com.lec.lec18.member.service.MemberService;

@Controller
public class MemberController {

	//@Repository annotation으로 생성한 객체를 주입
	@Resource(name="memService")
	MemberService service;
	
	@RequestMapping(value="/memLogin1", method=RequestMethod.POST)
	public String memLogin1(Model model, HttpServletRequest request) {
		//1. HttpServletRequest 를 통해 전송 정보 받기
		String memId = request.getParameter("memId");
		String memPw = request.getParameter("memPw");
		
		Member member = service.memberSearch(memId, memPw);
		
		try {
			model.addAttribute("memId", member.getMemId());
			model.addAttribute("memPw", member.getMemPw());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "memLoginOk";
	}
	
	@RequestMapping(value="/memLogin2", method=RequestMethod.POST)
	public String memLogin2(Model model, @RequestParam("memId") String memId, @RequestParam("memPw") String memPw) {
		//2. @RequestParam 어노테이션을 통해 전송 정보 받기

		Member member = service.memberSearch(memId, memPw);
		
		try {
			model.addAttribute("memId", member.getMemId());
			model.addAttribute("memPw", member.getMemPw());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "memLoginOk";
	}
	
	
	@RequestMapping(value="/memJoin", method=RequestMethod.POST)
	public String memJoin(Member member) {
		//3. command 객체를 이용해서 전송 정보 받기, model도 필요 없음!
		
		service.memberRegister(member.getMemId(), member.getMemPw(), member.getMemMail()
				, member.getMemPhone1(), member.getMemPhone2(), member.getMemPhone3());
		
		return "memJoinOk";
	}
	
}