package kr.or.ddit.vo;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Member {
	//@NotBlank을 붙이는 순간, 입력값 검증 규칙을 지정한다
	//특징 : 문자열이 null이 아니고, trim한 길이가 0보다 크다는 것을 검사한다.
	@NotBlank(message = "너무 졸리당")
	private String userId = "a001";
	@NotBlank(message = "쉬는시간에 자야지")
	private String password = "1234";
	@NotBlank(message = "짜란~")
	@Size(max=3)
	private String userName = "hongkd";
	private int coin = 200;
	//과거 날짜인지를 검사한다
	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;
	
	//이메일 주소 형식인지를 검사한다
	@Email
	private String email;
	private String gender;
	private String hobby;
	private String[] hobbyArray;
	private List<String> hobbyList;
	private boolean foreigner;
	private String developer;
	private String nationality;
	
	//중첩된 자바빈즈의 입력값 검증을 활성화한다
	@Valid
	private Address address;
	@Valid
	private List<Card> cardList;
	private String car;
	private String[] carArray;
	private List<String> carList;
	
	private String introduction;
	private String birthDay;
	
}
