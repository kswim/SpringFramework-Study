package com.lec.lec14;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller //HandlerMapping에서 Controller를 찾을 수 있도록 annotation이 필요함
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET) //HandlerAdapter에서 요청에 대한 method를 찾을 때 RequestMapping의 value를 보고 찾음 
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		//model 객체에 데이터를 담아서 Dispatcher에게 전달할 수 있음
		
		return "home"; //스프링 설정파일의 InternalResourceViewResolver에서 return값에 prefix와 suffix를 붙여서 해당하는 view를 찾음
	}
	
}
