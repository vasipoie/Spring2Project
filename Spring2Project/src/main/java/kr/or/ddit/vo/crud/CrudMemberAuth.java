package kr.or.ddit.vo.crud;

import lombok.Data;

@Data
//한 명의 회원이 여러개의 권한을 갖고 있는 구조
public class CrudMemberAuth {
	private int userNo;
	private String auth;
}
