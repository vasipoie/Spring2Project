package kr.or.ddit.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.vo.AddressBook;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/test")
@Slf4j
public class TestController02 {
	
	//db static로
	private static ArrayList<AddressBook> addressBookList;
	private static ArrayList<AddressBook> bookmarkList;
	
	// TestController02 객체 생성 -> 본인 생성자 실행 -> 
	// @PostConstruct 어노테이션 조회 -> 찾으면 가장 먼저 실행
	
	@PostConstruct
	public void init() {
		addressBookList = new ArrayList<>();
		bookmarkList = new ArrayList<>();
		createAddressBook();
	}
	
	public void createAddressBook() {
		AddressBook ab1 = new AddressBook();
		AddressBook ab2 = new AddressBook();
		AddressBook ab3 = new AddressBook();
		
		ab1.setName("최윤서");
		ab1.setGender("남자");
		ab1.setNickName("공부하고싶다");
		ab1.setProfileImg("1.png");
		ab1.setEmail("dbstj9409@naver.com");
		ab1.setJob("개발자꿈나무");
		ab1.setPhone1("010");
		ab1.setPhone2("1234");
		ab1.setPhone3("5678");
		
		ab2.setName("홍진영");
		ab2.setGender("남자");
		ab2.setNickName("모범생");
		ab2.setProfileImg("2.png");
		ab2.setEmail("wlsdud1234@naver.com");
		ab2.setJob("학생");
		ab2.setPhone1("010");
		ab2.setPhone2("2345");
		ab2.setPhone3("6789");
		
		ab3.setName("임민혁");
		ab3.setGender("남자");
		ab3.setNickName("몸짱");
		ab3.setProfileImg("3.png");
		ab3.setEmail("alsgur1122@naver.com");
		ab3.setJob("헬스트레이너");
		ab3.setPhone1("010");
		ab3.setPhone2("9999");
		ab3.setPhone3("8888");
		
		addressBookList.add(ab1);
		addressBookList.add(ab2);
		addressBookList.add(ab3);
//		bookmarkList.add(ab1);
	}

	//동기로 쓰려고
	@RequestMapping(value="/addressBook.do")
	public String addressBook(Model model) {
		
		return "script/addressBook2";
	}
	
	//json형식으로 데이터 반환
	@RequestMapping(value = "/getList.do", method = RequestMethod.GET, produces ="application/json; charset=UTF-8")
	public ResponseEntity<String> getList() {
		//list 자체를 json형태로 변환 후 보냄
		String jsonData = getListForJsonData(addressBookList);
		log.info(jsonData);
		
		return new ResponseEntity<String>(jsonData, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getBookMarkList.do", method=RequestMethod.GET, produces ="application/json; charset=UTF-8")
	public ResponseEntity<String> getBookMarkList(){
		String jsonData = getListForJsonData(bookmarkList);
		log.info("jsonData >> " + jsonData);
		return new ResponseEntity<String>(jsonData, HttpStatus.OK);
	}

	@RequestMapping(value="/addAddress.do", method=RequestMethod.GET)
	public String addAddress(){
		return "script/addAddress2";
	}
	
	//비동기 방식으로 접근하니까 String -> ResponseEntity
	//@RequestBody -> json형식의 데이터를 받아올 때만 필요
	@RequestMapping(value="/addAddress.do", method=RequestMethod.POST)
	public ResponseEntity<String> addAddressPost(@RequestBody AddressBook addressBook){
		
		String gender ="남자";
		if(addressBook.getGender().equals("female")) {
			gender ="여자";
		}
		addressBook.setGender(gender);
		addressBookList.add(addressBook);
		
		return new ResponseEntity<String>("success",HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value="/removeAddress.do", method=RequestMethod.POST)
	public ResponseEntity<String> removeAddress(@RequestBody String id){
		//id를 받아오면, id:1 이렇게 받아옴 -> 이런방식으로 받아오면 안됨
		//단일데이터를 받아오는 방식인 Map<String, String> map 사용
		//방식1
//		ObjectMapper objMapper = new ObjectMapper();
//		
//		try {
//			JsonNode node = objMapper.readTree(id);
//			//HTML NODE 형식을 읽어오는데 TREE 형식으로 읽어옴
//			//XML <html><head></head><body><div></div></body>
//			
//			//field에 대한 id 키 값을 넣고
//			int no = node.get("id").asInt();
//			
//			System.out.println(">> " + no);
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		ObjectMapper objMapper = new ObjectMapper();
		
		try {
			JsonNode node = objMapper.readTree(id);
			//HTML NODE 형식을 읽어오는데 TREE 형식으로 읽어옴
			//XML <html><head></head><body><div></div></body>
			
			//field에 대한 id 키 값을 넣고
			int no = node.get("id").asInt();
			
			Iterator<AddressBook> it = addressBookList.iterator();
			
			while(it.hasNext()) {
				AddressBook item = it.next();
				if(item.getNo() == no) {
					bookmarkList.add(item);
					it.remove();
					break;
				}
			}
			log.info("addressBookList : {}",addressBookList);
			log.info("bookmarkList : {}",bookmarkList);
			
			System.out.println(">> " + no);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>("success",HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value="/removeBookMark.do", method=RequestMethod.POST)
	public ResponseEntity<String> removeBookMark(@RequestBody Map<String, String> map) {
		
		int no = Integer.parseInt(map.get("id"));
		
		ArrayList<AddressBook> copyList = new ArrayList<AddressBook>(bookmarkList);
		copyList.forEach(item -> {
			if(item.getNo() == no) {
				addressBookList.add(item);
				bookmarkList.remove(item);
			}
		});
		
		return new ResponseEntity<String>("success", HttpStatus.OK);
		
	}
	
	
	
	//모든 리스트를 받으려고 제너릭타입 선언x
	private String getListForJsonData(List list) {
		
		String jsonData = "";
		// 반환해줄 JSON DATA
		
		ObjectMapper objMapper = new ObjectMapper();
		// JACKSON DATABIND 라이브러리 안에 들어있는 클래스
		
		try {
			jsonData = objMapper.writeValueAsString(list);
			// LIST 데이터를 JSON 스트링 형태로 변환 시킨다
			//{"no":1, "name":"이명문"} -> 1, 이명문
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return jsonData;
	}
}
