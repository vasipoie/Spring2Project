package kr.or.ddit.controller.model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/redirect")
@Slf4j
public class RedirectAttributeMemberController {
	
	/*
	 * 4. RedirectAttribute 타입
	 * - RedirectAttribute는 일회성으로 데이터를 전달하는 용도로 사용한다.
	 */
	
	
	@RequestMapping(value="/registerForm", method=RequestMethod.GET)
	public String registerForm() {
		return "member/registerRedirectAttributeForm";
	}
	
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(Member member, RedirectAttributes ra) {
		log.info("register() 실행...!");
		log.info("member.getUserId() : "+member.getUserId());
		log.info("member.getPassword() : "+member.getPassword());
		
		ra.addFlashAttribute("msg", "success");
		
		return "redirect:/redirect/result";
		
	}
	
	@RequestMapping(value="/result", method=RequestMethod.GET)
	public String result() {
		log.info("register() 실행...!");
		return "result";
	}
	
	
}
