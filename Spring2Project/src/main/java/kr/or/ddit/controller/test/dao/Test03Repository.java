package kr.or.ddit.controller.test.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.controller.test.vo.StudentVO;

@Repository
public class Test03Repository {

	private List<StudentVO> studentList = new ArrayList<StudentVO>();
	
	private String[] ids = {"a001","b001","c001","d001","e001","f001"};
	private String[] pws = {"1234","1234","1234","1234","1234","1234"};
	private String[] names = {"안희재","김현태","김나혜","김영상","최현명","신하림"};
	private String[] phones = {
		"011-1122-4783","016-4734-1523","010-1234-1234","019-9879-2243","010-4536-1234",
		"010-7564-4352"
	};
	
	public Test03Repository() {
		// StudentVO를 활용하여 403호 학생 5명을 초기화해주세요.
		for(int i = 0; i < ids.length; i++) {
			StudentVO student = new StudentVO();
			student.setMemId(ids[i]);
			student.setMemPw(pws[i]);
			student.setMemName(names[i]);
			student.setMemEmail(ids[i] + "@naver.com");
			student.setMemPhone(phones[i]);
			studentList.add(student);
		}
	}
	
	// 기능구현
	// - 학생 전체 가져오기
	// - 학생 한명 정보 가져오기
	// - 이름, 이메일 정보를 활용해 학생 아이디 가져오기
	// - 아이디, 이름, 이메일 정보를 활용해 학생 비밀번호 가져오기
	// 등등 필요에 의한 기능을 구현해주세요.
	
	public StudentVO memOne(String memId, String memPw) {
		
		StudentVO sv = new StudentVO();
		
		for(int i=0; i<studentList.size(); i++) {
			System.out.println(studentList.get(i));
			if(studentList.get(i).getMemId().equals(memId) 
					&& studentList.get(i).getMemPw().equals(memPw)) {
				sv.setMemId(studentList.get(i).getMemId());
				sv.setMemPw(studentList.get(i).getMemPw());
				sv.setMemName(studentList.get(i).getMemName());
				sv.setMemEmail(studentList.get(i).getMemEmail());
				sv.setMemPhone(studentList.get(i).getMemPhone());
				break;
			}
		}
		return sv;
	}
	
}
