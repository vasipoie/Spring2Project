package kr.or.ddit.controller.jsp.textfield;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/formtag/textfield")
@Slf4j
public class JSPFormTextFieldTagController {

	/*
	 * 4. 텍스트 필드 요소
	 * 	- HTML 텍스트 필드를 출력하려면 <form:input> 요소를 사용한다.
	 */
	
	// 1) 모델에 기본생성자로 생성한 폼 객체를 추가한 후에 화면에 전달한다
	@RequestMapping(value="/registerForm01", method=RequestMethod.GET)
	public String registerForm01(Model model) {
		log.info("registerForm01() 실행...!");
		model.addAttribute("member", new Member());
		return "home/formtag/textfield/registerForm01";
	}
	
	// 1의 값을 변경했을 때도 잘 들어가있는지 확인
	// 내가 넘길 데이터를 자바 빈즈 객체에 넣어주면 자동 바인딩
	@RequestMapping(value="/registerForm02", method=RequestMethod.GET)
	public String registerForm02(Model model) {
		log.info("registerForm02() 실행...!");
		Member member = new Member();
		member.setUserId("hong123");
		member.setUserName("홍길순");
		member.setEmail("ddit@ddit.or.kr");
		model.addAttribute("member", member);
		return "home/formtag/textfield/registerForm01";
	}
	
	
	
}
