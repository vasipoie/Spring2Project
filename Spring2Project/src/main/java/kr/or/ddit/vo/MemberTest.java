package kr.or.ddit.vo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MemberTest {
	private String userId;
	private String password;
	private String userName;
	private String email;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;
	private String gender;
	private String developer;
	
	private boolean foreigner;
	private String fore;
	
	private String nationality;
	private String cars;
	private String hobby;
	private String introduction;
	
	private boolean agree;
	private String ag;
	
	private Address address;
	private List<Card> cardList;
}
