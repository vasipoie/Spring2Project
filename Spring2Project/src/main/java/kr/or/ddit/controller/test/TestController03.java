package kr.or.ddit.controller.test;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.controller.test.dao.Test03Repository;
import kr.or.ddit.controller.test.vo.StudentVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/test03")
@Slf4j
public class TestController03 {
	
	@Inject
	Test03Repository t3Repo = new Test03Repository();

	@RequestMapping(value="/login.do", method = RequestMethod.GET)
	public String loginPage() {
		return "script/test03/login";
	}
	
	@RequestMapping(value="/findInfo.do", method = RequestMethod.GET)
	public String findInfo() {
		return "script/test03/findInfo";
	}
	
	@RequestMapping(value="/info.do", method = RequestMethod.GET)
	public String info(HttpSession session) {
		log.info("info() 실행...!");
		log.info("session : "+session.getAttribute("studentVO"));
		return "script/test03/info";
	}
	
	@RequestMapping(value="/infoPage.do", method = RequestMethod.POST)
	public String infoPage(
			@RequestBody StudentVO sv
			, RedirectAttributes ra
			, HttpSession session
			){
		log.info("infoPage() 실행...!");
		
		StudentVO studentVO = t3Repo.memOne(sv.getMemId(), sv.getMemPw());
		if(studentVO != null) {
			log.info("studentVO : "+studentVO);
			session.setAttribute("studentVO", studentVO);
			ra.addFlashAttribute("msg", studentVO.getMemName()+"님! 환영합니다!");
		}
		return "redirect:/test03/info.do";
	}
	
}
