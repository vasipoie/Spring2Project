package kr.or.ddit.controller.crud.notice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notice")
public class NoticeProfileController {
	
	@GetMapping(value="/profile.do")
	public String noticeProfile() {
		return "notice/profile";
	}
}
