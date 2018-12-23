package com.lec.lec16;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String home(Model model) {
		System.out.println("----- home() method -----");
		model.addAttribute("key", "home value");
		return "home";
	}
	
	@RequestMapping("/login")
	public String login(Model model) {
		System.out.println("----- login() method -----");
		model.addAttribute("key", "login value");
		return "login";
	}
}
