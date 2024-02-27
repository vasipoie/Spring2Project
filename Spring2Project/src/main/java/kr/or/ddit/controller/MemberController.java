package kr.or.ddit.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.vo.Address;
import kr.or.ddit.vo.Card;
import kr.or.ddit.vo.FileMember;
import kr.or.ddit.vo.Member;
import kr.or.ddit.vo.MemberTest;
import kr.or.ddit.vo.MultiFileMember;
import lombok.extern.slf4j.Slf4j;

/*
 * 5장 컨트롤러 요청 처리
 * 
 * 		1. 컨트롤러 메소드 매개변수
 * 			Model
 * 			- 이동 대상에 전달할 데이터를 가지고 있는 인터페이스
 * 			RedirectAttributes
 * 			- 리다이렉트 대상에 전달할 데이터를 가지고 있는 인터페이스
 * 			MultipartFile
 * 			- 멀티파트 요청을 사용해 업로드 된 파일 정보를 가지고 있는 인터페이스
 * 			BindingResult
 * 			- 도메인 클래스의 입력값 검증을 가지고 있는 인터페이스
 * 			java.security.Principal
 * 			- 클라이언트 인증을 위한 사용자 정보를 가지고 있는 인터페이스
 */
@Controller
@Slf4j
public class MemberController {

//	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	
	/*
	 * 5장 컨트롤러 요청 처리 시작 컨트롤러 메소드
	 * 	- 페이지를 요청해 테스트를 진행할 페이지입니다.
	 */
	@RequestMapping(value="/registerForm", method=RequestMethod.GET)
	public String registerForm() {
		log.info("registerForm() 실행...!");
		return "member/registerForm";
	}
	
	// 1) URL 경로 상의 쿼리 파라미터 정보로부터 요청 데이터를 취득할 수 있다.
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String registerByParameter(String userId, String password) {
		log.info("registerByParameter() 실행...!");
		log.info("userId : " + userId);
		log.info("password : " + password);
		return "success";
	}
	
	// 2) URL 경로 상의 경로 변수로부터 요청 데이터를 취득할 수 있다.
	@RequestMapping(value="/register/{userId}", method=RequestMethod.GET)
	public String registerByPath(@PathVariable String userId) {
		log.info("registerByPath() 실행...!");
		log.info("userId : " + userId);
		return "success";
	}
	
	
	// 3) HTML Form 필드명(name)과 컨트롤러 매개변수명이 일치하면 요청 데이터를 취득할 수 있다.
	// 메소드에서 파라미터 순서는 상관없고, 받는 파라미터명과 name의 값이 같다면 값으로 바인딩된다.
	@RequestMapping(value="/register01", method = RequestMethod.POST)
	public String register01(String password, String userId) {
		log.info("register01() 실행...!");
		log.info("userId : " + userId);
		log.info("password : " + password);
		return "success";
	}
	
	// 4) HTML Form 필드값이 숫자일 경우에는 컨트롤러 매개변수 타입이 숫자형이면 숫자로 타입변환하여 데이터를 취득하는가?
	// 넘겨받은 파라미터를 컨트롤러 메소드 내에서 사용하고 싶은 타입으로 사용할 수 있다.
	@RequestMapping(value="/register02", method = RequestMethod.POST)
	public String register02(String password, String userId, int coin) {
		log.info("register02() 실행...!");
		log.info("userId : " + userId);
		log.info("password : " + password);
		log.info("coin : " + coin);
		return "success";
	}
	
	
	/*
	 * 3. 요청 데이터 처리 어노테이션
	 * 
	 * 		@PathVariables
	 * 		- URL에서 경로 변수 값을 가져오기 위한 어노테이션
	 * 		@RequestParam
	 * 		- 요청 파라미터 값을 가져오기 위한 어노테이션
	 * 		@RequestHeader
	 * 		- 요청 헤더 값을 가져오기 위한 어노테이션
	 * 		@RequestBody
	 * 		- 요청 본문 내용을 가져오기 위한 어노테이션
	 * 		@CookieValue
	 * 		- 쿠키 값을 가져오기 위한 어노테이션
	 */
	
	@RequestMapping(value="/register/{userId}/{coin}" ,method=RequestMethod.GET)
	public String registerByPath(
			@PathVariable String userId, @PathVariable int coin) {
		log.info("registerByPath() 실행...!");
		log.info("userId : " + userId);
		log.info("coin : "+coin);
		return "success";
	}
	
	
	//2) @RequestParam 어노테이션을 사용하여 특정한 HTML Form의 필드명을 지정하여 요청을 처리한다.
	//받아온 필드명을 다른 변수명으로 쓰고 싶을 때
	// 클라이언트에서 선언된 필드명은 userId인데 서버 컨트롤러 메소드에서 받는 필드명이 username이면 파라미터를 받을 수 없다.
	// 데이터를 받는 필드명은 동일하게 userId로 하되, 사용하는 변수명을 username으로 달리 설정할 수 있는데,
	// @RequestParam이 이를 설정해준다.
	// Spring1에서 페이징을 구현할 때 page를 넘기는 방법을 이 녀석을 채택하여 구현
	@RequestMapping(value="/register0202", method=RequestMethod.POST)
	public String register0202(
			@RequestParam("userId") String username, String password, int coin
			) {
		log.info("register0202() 실행...!");
		log.info("userId : " + username);
		log.info("password : " + password);
		log.info("coin : " + coin);
		return "success";
	}
	
	
	/*
	 * 4. 요청 처리 자바빈즈
	 */
	
	// 1) 폼 텍스트 필드 요소값을 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/beans/register01", method=RequestMethod.POST)
	public String registerJavaBeans01(Member member) {
		log.info("registerJavaBeans01() 실행...!");
		log.info("member.getUserId() : " + member.getUserId());
		log.info("member.getPassword() : " + member.getPassword());
		log.info("member.getCoin() : " + member.getCoin());
		return "success";
	}
	
	// 2) 여러 개의 폼 텍스트 필드 요소값을 매개변수 순서와 상관없이 매개변수명을 기준으로 처리한다.
	@RequestMapping(value="/beans/register03", method=RequestMethod.POST)
	public String registerJavaBeans03(Member member, int coin) {
		log.info("registerJavaBeans03() 실행...!");
		log.info("member.getUserId() : " + member.getUserId());
		log.info("member.getPassword() : " + member.getPassword());
		log.info("member.getCoin() : " + member.getCoin());
		log.info("coin : " + coin);
		return "success";
	}
	
	
	/*
	 * 5. Data 타입 처리
	 * - 스프링 MVC는 Data 타입의 데이터를 처리하는 여러 방법을 제공한다.
	 * - 따로 지정하지 않으면 변환에 적합한 날짜 문자열 형식이 어떤 것이 있는지 알아봅시다!
	 */
	
	//1) 쿼리 파라미터(dateOfBirth=12341234)로 전달받은 값이 날짜 문자열 형식에 맞지 않아 Date 타입으로 변환에 실패한다.
	//2) 쿼리 파라미터(dateOfBirth=2024-02-23)로 전달받았을 때 날짜로 변환이 되는가?
	//3) 쿼리 파라미터(dateOfBirth=2024/02/23)로 전달받았을 때 날짜로 변환이 되는가?
	@RequestMapping(value="/registerByGet01", method=RequestMethod.GET)
	public String registerByGet01(String userId, Date dateOfBirth) {
		log.info("registerByGet01() 실행...!");
		log.info("userId : "+userId);
		log.info("dateOfBirth : "+dateOfBirth);
		
		//날짜 데이터를 넘겨줄 때에는 '년/월/일'의 형태로 넘겨주어야 Date 타입의 변수값을 바인딩 할 수 있다.
		
		return "success";
	}
	
	//4) Member 매개변수와 쿼리 파라미터(dateOfBirth-20240223)로 전달받은 값이 날짜 문자열 형식으로 설정 시, 받는가?
	@RequestMapping(value="/registerByGet02", method=RequestMethod.POST)
	public String registerByGet02(Member member) {
		log.info("registerByGet02() 실행...!");
		log.info("userId : "+member.getUserId());
		log.info("dateOfBirth : "+member.getDateOfBirth());
		return "success";
	}
	
	
	/*
	 * 6. @DateTimeFormat 어노테이션
	 * - @DateTimeFormat 어노테이션의 pattern 속성값에 원하는 날짜 형식을 지정할 수 있다.
	 */
	
	//위에 1,2,3 같이 있는거 이걸로 고친 후 돌리면 2번은 success로 뜸
	//@DateTimeFormat 어노테이션으로 원하는 날짜 형식을 지정해놔서
//	@RequestMapping(value="/registerByGet01", method=RequestMethod.GET)
//	public String registerByGet01(String userId, @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth) {
//		log.info("registerByGet01() 실행...!");
//		log.info("userId : "+userId);
//		log.info("dateOfBirth : "+dateOfBirth);
//		
//		//날짜 데이터를 넘겨줄 때에는 '년/월/일'의 형태로 넘겨주어야 Date 타입의 변수값을 바인딩 할 수 있다.
//		
//		return "success";
//	}
	
	
	/*
	 * 7. 폼 방식 요청 처리
	 */
	//1) 폼 텍스트 필드 요소 값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerUserId", method=RequestMethod.POST)
	public String registerUserId(String userId) {
		log.info("registerUserId() 실행...!");
		log.info("userId : "+userId);
		return "success";
	}
	
	@RequestMapping(value="/registerPassword", method=RequestMethod.POST)
	public String registerPassword(Member member) {
		log.info("registerPassword() 실행...!");
		log.info("member.getPassword() : " + member.getPassword());
		return "success";
	}
	
////////////////////////////////////////////////////////////////////////////////////////
	
	//3) 폼 라디오버튼 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerRadio", method=RequestMethod.POST)
	public String registerRadio(String gender) {
		log.info("registerRadio() 실행...!");
		log.info("gender : "+gender);
		return "success";
	}
	
////////////////////////////////////////////////////////////////////////////////////////
	
	//4) 폼 셀렉트 박스 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerSelect", method=RequestMethod.POST)
	public String registerSelect(String nationality) {
		log.info("registerSelect() 실행...!");
		log.info("nationality : "+nationality);
		return "success";
	}
	
	//5) 복수 선택이 가능한 폼 셀렉트 박스 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerMultipleSelect01", method=RequestMethod.POST)
	public String registerMultipleSelect01(String cars) {
		log.info("registerMultipleSelect01() 실행...!");
		log.info("cars : "+cars);
		return "success";
	}
	
	//6) 복수 선택이 가능한 폼 셀렉트 박스 요소값을 문자열 배열 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerMultipleSelect02", method=RequestMethod.POST)
	public String registerMultipleSelect02(String[] carArray) {
		log.info("registerMultipleSelect02() 실행...!");
		log.info("carArray : "+carArray);
		
		if(carArray != null) {
			log.info("carArray.length : " + carArray.length);
			for(int i = 0; i < carArray.length; i++) {
				log.info("carArray["+i+"] : " + carArray[i]);
			}
		}else {
			log.info("carArray is null");
		}
		return "success";
	}
	
	//7) 복수 선택이 가능한 폼 셀렉트 박스 요소값을 문자열 배열 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerMultipleSelect03", method=RequestMethod.POST)
	public String registerMultipleSelect03(List<String> carList) {
		log.info("registerMultipleSelect03() 실행...!");
		log.info("carList : "+carList);
		
		//리스트로는 셀렉트 박스 값을 가져올 수 없습니다.
		//배열 형태를 이용하거나 객체를 이용하여 데이터를 얻어온다.
		if(carList != null && carList.size() > 0) {
			log.info("carList.size() : " + carList.size());
			for(int i = 0; i < carList.size(); i++) {
				log.info("carList.get("+i+") : " + carList.get(i));
			}
		}else {
			log.info("carList is null");
		}
		return "success";
	}

////////////////////////////////////////////////////////////////////////////////////////
	
	//8) 폼 체크박스 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerCheckbox01", method=RequestMethod.POST)
	public String registerCheckbox01(String hobby) {
		log.info("registerCheckbox01() 실행...!");
		log.info("hobby : "+hobby);
		return "success";
	}
	
	//9) 폼 체크박스 요소값을 문자열 배열 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerCheckbox02", method=RequestMethod.POST)
	public String registerCheckbox02(String[] hobbyArray) {
		log.info("registerCheckbox02() 실행...!");
		log.info("hobbyArray : "+hobbyArray);
		
		if(hobbyArray != null) {
			log.info("hobbyArray.length : " + hobbyArray.length);
			for(int i = 0; i < hobbyArray.length; i++) {
				log.info("hobbyArray["+i+"] : " + hobbyArray[i]);
			}
		}else {
			log.info("hobbyArray is null");
		}
		return "success";
	}
	
	//10) 폼 체크박스 요소값을 문자열 요소를 가진 리스트 컬렉션 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerCheckbox03", method=RequestMethod.POST)
	public String registerCheckbox03(ArrayList<String> hobbyList) {
		//받는 타입을 List로 하게되면 No primary or default constructor found for interface
		//java.util.List 에러 발생
		//스프링에서는 List 타입으로 데이터를 받는데에 문제가 발생합니다. (데이터 바인딩이 안됨)
		//리스트와 같은 형태의 값을 받으려면 String[]로 여러 데이터를 받아서 List에 담는 방법이나,
		//객체를 이용하여 데이터를 바인딩하는 방법이 있습니다.
		
		log.info("registerCheckbox03() 실행...!");
		
		if(hobbyList != null && hobbyList.size() > 0) {
			log.info("hobbyList.size() : " + hobbyList.size());
			for(int i = 0; i < hobbyList.size(); i++) {
				log.info("hobbyList.get("+i+") : " + hobbyList.get(i));
			}
		}else {
			log.info("hobbyList is null");
		}
		return "success";
	}
	
	//11) 폼 체크박스 요소값을 기본 데이터 타입인 불리언 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerCheckbox04", method=RequestMethod.POST)
	public String registerCheckbox04(boolean foreigner) {
		log.info("registerCheckbox04() 실행...!");
		log.info("foreigner : "+foreigner);
		//개인정보 동의와 같은 기능(스위칭 역할)을 만들 때 사용
		//체크된 값이 존재하면 value 속성에 설정된 true가 넘어오고,
		//체크된 값이 존재하지 않으면 false가 넘어온다.
		return "success";
	}
	
////////////////////////////////////////////////////////////////////////////////////////
	
	//12) 폼 텍스트 필드 요소값을 중첩된 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/registerUserAddress", method=RequestMethod.POST)
	public String registerUserAddress(Member member) {
		log.info("registerUserAddress() 실행...!");
		
		Address address = member.getAddress();
		if(address != null) {
			log.info("address.getPostCode() : "+address.getPostCode());
			log.info("address.getLocation() : "+address.getLocation());
		}else {
			log.info("address is null");
		}
		return "success";
		
	}
	
	//13) 폼 텍스트 필드 요소값을 중첩된 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/registerUserCardList", method=RequestMethod.POST)
	public String registerUserCardList(Member member) {
		log.info("registerUserCardList() 실행...!");
		
		List<Card> cardList = member.getCardList();
		
		if(cardList != null && cardList.size() > 0) {
			log.info("cardList.size() : " + cardList.size());
			
			for(int i=0; i<cardList.size(); i++) {
				Card card = cardList.get(i);
				log.info("card.getNo() : " + card.getNo());
				log.info("card.getValidMonth() : " + card.getValidMonth());
			}
		}else {
			log.info("cardList is null");
		}
		return "success";
		
	}
	
////////////////////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value="/registerAllForm", method=RequestMethod.GET)
	public String registerAllForm() {
		return "member/registerAllForm";
	}

	@RequestMapping(value="/registerUser", method=RequestMethod.POST)
	public String registerUser(
			MemberTest member
			, Model model
			) {
		//넘겨받은 데이터를 log를 통해서 문제에 작성되어있는 양식대로 출력해주세요.
		//데이터 전달자를 통해서 넘겨받은 데이터를 result페이지로 전달하여
		//log를 통해서 문제에 작성되어 있는 양식대로 화면을 출력해주세요.
		//result 페이지에서는 JSTL을 이용하여 EL표현문과 조합하여 출력해주세요.
		
		log.info("registerUser() 실행...!");
		
		Address address = member.getAddress();
		List<Card> cardList = member.getCardList();
		
		log.info("아이디 : "+member.getUserId());
		log.info("비밀번호 : "+member.getPassword());
		log.info("이름 : "+member.getUserName());
		log.info("Email : "+member.getEmail());
		
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		log.info("생년월일 : "+date.format(member.getDateOfBirth()));
		
		
		if(member.getGender().equals("male")) {
			member.setGender("남자");
		}else if(member.getGender().equals("female")) {
			member.setGender("여자");
		}else {
			member.setGender("기타");
		}
		log.info("성별 : "+member.getGender());
		
		
		if(member.getDeveloper().equals("Y")){
			member.setDeveloper("개발자");
		}else {
			member.setDeveloper("일반");
		}
		log.info("개발자 여부 : "+member.getDeveloper());
		
		
		if(member.isForeigner()) {
			member.setFore("외국인");
		}else {
			member.setFore("내국인");
		}
		log.info("외국인 여부 : "+member.getFore());
		
		
		if(member.getNationality().equals("korea")) {
			member.setNationality("대한민국");
		}else if(member.getNationality().equals("germany")) {
			member.setNationality("독일");
		}else if(member.getNationality().equals("austrailia")) {
			member.setNationality("호주");
		}else if(member.getNationality().equals("canada")) {
			member.setNationality("캐나다");
		}else if(member.getNationality().equals("usa")) {
			member.setNationality("미국");
		}else {
			member.setNationality("nationality is null");
		}
		log.info("국적 : "+member.getNationality());
		
		
//		if(member.getCars().equals("jeep")) {
//			member.setCars("JEEP");
//		}else if(member.getCars().equals("bmw")) {
//			member.setCars("BMW");
//		}else if(member.getCars().equals("audi")) {
//			member.setCars("AUDI");
//		}else if(member.getCars().equals("volvo")){
//			member.setCars("VOLVO");
//		}else {
//			member.setCars("car is null");
//		}
		
		String car = member.getCars();
//		log.info("car : "+car);
		String carStr = car.toUpperCase();
//		log.info("carStr : "+carStr);
//		if(car.contains(",")) {
//			String[] carArr = car.split(",");
//			for(int i=0; i<carArr.length; i++) {
//				log.info(carArr[i]);
//				if(carArr[i].equals("jeep")) {
//					car += " JEEP";
//				}else if(carArr[i].equals("bmw")) {
//					car += "BMW";
//				}else if(carArr[i].equals("audi")) {
//					car += "AUDI";
//				}else if(carArr[i].equals("volvo")){
//					car += "VOLVO";
//				}
//				car += carArr[i].toUpperCase();
//			}
			member.setCars(carStr);
//		}
		log.info("소유차량 : "+carStr);
		
		String hobby = member.getHobby();
		hobby = hobby.replace("sports",  "운동").replace("music", "음악감상").replace("movie", "영화시청");
		log.info("hobby : "+ hobby);
		member.setHobby(hobby);
		
		log.info("취미 : "+member.getHobby());
		
		
		if(address != null) {
			log.info("우편번호 : "+address.getPostCode());
			log.info("주소 : "+address.getLocation());
		}else {
			log.info("address is null");
		}
		
		
		if(cardList != null && cardList.size() > 0) {
			for(int i=0; i<cardList.size(); i++) {
				Card card = cardList.get(i);
				log.info("카드"+(i+1)+"-번호 : "+card.getNo());
				log.info("카드"+(i+1)+"-유효년월 : "+date.format(card.getValidMonth()));
			}
		}else {
			log.info("cardList is null");
		}
		
		
		log.info("소개 : "+member.getIntroduction());
		
		if(member.isAgree()) {
			member.setAg("동의함");
		}else {
			member.setAg("동의하지않음");
		}
		log.info("개인정보동의 : "+member.getAg());
		
		
		model.addAttribute("member", member);
		return "member/result";
	}
	
////////////////////////////////////////////////////////////////////////////////////////
	
	/*
	 * 8. 파일 업로드 폼 방식 요청 처리
	 * 	
	 * 		> 파일 업로드 폼 방식 요청 처리를 위한 의존 라이브러리 추가
	 * 		> pom.xml내 commons-fileupload, common-io 라이브러리 의존관계 등록
	 * 		> web.xml에 모든 경로에 대한 MultipartFilter를 등록
	 */
	
	//1) 파일업로드 폼 파일 요소값을 스프링 MVC가 지원하는 MultipartFile 매개변수로 처리한다.
	@RequestMapping(value = "/registerFile01", method=RequestMethod.POST)
	public String registerFile01(MultipartFile picture) {
		log.info("registerFile01() 실행...!");
		log.info("originalName : " + picture.getOriginalFilename());
		log.info("fileSize : " + picture.getSize());
		log.info("contentType : " + picture.getContentType());
		return "success";
	}
	
	//2) 파일업로드 폼 파일 요소값과 텍스트 필드 요소값을 MultipartFile 매개변수와 문자열 매개변수로 처리한다.
	@RequestMapping(value="/registerFile02", method = RequestMethod.POST)
	public String registerFile02(String userId, String password, MultipartFile picture) {
		log.info("registerFile02() 실행...!");
		log.info("userId : " + userId);
		log.info("password : " + password);
		
		log.info("originalName : " + picture.getOriginalFilename());
		log.info("fileSize : " + picture.getSize());
		log.info("contentType : " + picture.getContentType());
		return "success";
	}
	
	//3) 파일업로드 폼 파일 요소값과 텍스트 필드 요소값을 MultipartFile 매개변수와 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/registerFile03", method = RequestMethod.POST)
	public String registerFile03(Member member, MultipartFile picture) {
		log.info("registerFile03() 실행...!");
		log.info("member.getUserId() : " + member.getUserId());
		log.info("member.getPassword() : " + member.getPassword());
		
		log.info("originalName : " + picture.getOriginalFilename());
		log.info("fileSize : " + picture.getSize());
		log.info("contentType : " + picture.getContentType());
		return "success";
	}
	
	//4) 파일업로드 폼 파일 요소값과 텍스트 필드 요소값을 FileMember 타입의 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/registerFile04", method = RequestMethod.POST)
	public String registerFile04(FileMember fileMember) {
		log.info("registerFile04() 실행...!");
		
		MultipartFile picture = fileMember.getPicture();
		
		log.info("fileMember.getUserId() : " + fileMember.getUserId());
		log.info("fileMember.getPassword() : " + fileMember.getPassword());
		
		log.info("originalName : " + picture.getOriginalFilename());
		log.info("fileSize : " + picture.getSize());
		log.info("contentType : " + picture.getContentType());
		return "success";
	}
	
	//5) 여러개의 파일업로드를 폼 파일 요소값을 여러 개의 MultipartFile 매개변수로 처리한다.
	@RequestMapping(value="/registerFile05", method = RequestMethod.POST)
	public String registerFile05(MultipartFile picture, MultipartFile picture2) {
		log.info("registerFile05() 실행...!");
		
		log.info("originalName : " + picture.getOriginalFilename());
		log.info("fileSize : " + picture.getSize());
		log.info("contentType : " + picture.getContentType());
		
		log.info("originalName2 : " + picture2.getOriginalFilename());
		log.info("fileSize2 : " + picture2.getSize());
		log.info("contentType2 : " + picture2.getContentType());
		return "success";
	}
	
	//6) 여러 개의 파일업로드를 폼 파일 요소값을 MultipartFile 타입의 요소를 가진 리스트 컬렉션 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerFile06", method = RequestMethod.POST)
	public String registerFile06(List<MultipartFile> pictureList) {
		log.info("registerFile06() 실행...!");
		log.info("pictureList.size() : " + pictureList.size());
		
		for(int i=0; i<pictureList.size(); i++) {
			MultipartFile picture = pictureList.get(i);
			log.info("originalName : " + picture.getOriginalFilename());
			log.info("fileSize : " + picture.getSize());
			log.info("contentType : " + picture.getContentType());
		}
		
		return "success";
	}
	
	//7) 여러 개의 파일업로드를 폼 파일 요소값과 텍스트 필드 요소값을 MultiFileMember 타입의 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/registerFile07", method = RequestMethod.POST)
	public String registerFile07(MultiFileMember multiFileMember) {
		log.info("registerFile07() 실행...!");
		
		List<MultipartFile> pictureList = multiFileMember.getPictureList();
		
		log.info("multiFileMember.getUserId() : " + multiFileMember.getUserId());
		
		log.info("pictureList.size() : " + pictureList.size());
		
		for(int i=0; i<pictureList.size(); i++) {
			MultipartFile picture = pictureList.get(i);
			log.info("originalName : " + picture.getOriginalFilename());
			log.info("fileSize : " + picture.getSize());
			log.info("contentType : " + picture.getContentType());
		}
		
		return "success";
	}
	
	//8) 파일업로드 폼 파일 요소값과 텍스트 필드 요소값을 MultipartFile 타입의 배열 매개변수로 처리한다.
	@RequestMapping(value="/registerFile08", method = RequestMethod.POST)
	public String registerFile08(MultipartFile[] pictureArray) {
		log.info("registerFile08() 실행...!");
		log.info("pictureArray.length : " + pictureArray.length);	
		
		for(int i=0; i < pictureArray.length; i++) {
			log.info("originalName : " + pictureArray[i].getOriginalFilename());
			log.info("fileSize : " + pictureArray[i].getSize());
			log.info("contentType : " + pictureArray[i].getContentType());
		}
		
		return "success";
	}
	
	
	
	
	
	
	
	
}
