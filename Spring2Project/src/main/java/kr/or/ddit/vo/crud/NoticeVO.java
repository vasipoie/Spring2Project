package kr.or.ddit.vo.crud;

import lombok.Data;

@Data
public class NoticeVO {
	private int boNo;
	private String boTitle;
	private String boContent;
	private String boWriter;
	private String boDate;	//날짜 데이터를 꼭 Date가 아닌 String으로 해도 된다 ex)풀캘린더api에서는 String으로 받아야 사용가능함
	private int boHit;
	
//	private Integer[] delNoticeNo;
//	private MultipartFile[] boFile;
//	private List<NoticeFileVO> noticeFileList;
	
}
