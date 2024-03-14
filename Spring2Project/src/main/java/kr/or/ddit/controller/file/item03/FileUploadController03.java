package kr.or.ddit.controller.file.item03;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.service.IItemService3;
import kr.or.ddit.vo.Item3;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/item3")
@Slf4j
public class FileUploadController03 {
   /*
    * 13장 파일업로드
    * 
    * 4. 비동기 방식 업로드 (썸네일이 나오는)
    * - 비동기 방식으로 여러 개의 이미지를 업로드 하는 파일 업로드 기능을 구현한다.
    * 
    *       # 환경 설정
    *       - 의존 관계 정의(pom.xml 설정)
    *          > commons-io : 파일을 처리하기 위한 의존 라이브러리
    *          > imgscalr-lib : 이미지 변환을 처리하기 위한 의존 라이브러리
    *          > jackson - databind : json 데이터 바인딩을 위한 의존 라이브러리
    * 
    *       # 파일 업로드 구현 설명
    * 
    *          - 파일 업로드 등록 화면 컨트롤러 만들기 ( FileUploadController03 )
    *          - 파일 업로드 등록 화면 컨트롤러 메소드 만들기 (item3RegisterForm:get)
    *          - 파일 업로드 등록 화면 만들기 (item3/register.jsp)
    *          - 여기까지 확인
    *          
    *          - 파일 업로드 등록 기능 컨트롤러 메소드 만들기(item3Register:post)
    *          - 파일 업로드 등록 기능 서비스 인터페이스 메소드 만들기
    *          - 파일 업로드 등록 기능 서비스 클래스 메소드 만들기
    *          - 파일 업로드 등록 기능 Mapper 인터페이스 메소드 만들기
    *          - 파일 업로드 등록 기능 Mapper xml 쿼리 만들기
    *          - 파일 업로드 등록 완료 페이지 만들기
    *          - 여기까지 확인
    *          
    *          - 파일 업로드 목록 화면 컨트롤러 메소드 만들기(item3List:get)
    *          - 파일 업로드 목록 화면 서비스 인터페이스 메소드 만들기
    *          - 파일 업로드 목록 화면 서비스 클래스 메소드 만들기
    *          - 파일 업로드 목록 화면 Mapper 인터페이스 메소드 만들기
    *          - 파일 업로드 목록 화면 Mapper xml 쿼리 만들기
    *          - 파일 업로드 목록 완료 페이지 만들기(item3/list.jsp)
    *          - 여기까지 확인
    *          
    *          - 파일 업로드 수정 화면 컨트롤러 메소드 만들기(item3ModifyForm:get)
    *          - 파일 업로드 수정 화면 서비스 인터페이스 메소드 만들기
    *          - 파일 업로드 수정 화면 서비스 클래스 메소드 만들기
    *          - 파일 업로드 수정 화면 Mapper 인터페이스 메소드 만들기
    *          - 파일 업로드 수정 화면 Mapper xml 쿼리 만들기
    *          - 파일 업로드 수정 화면 만들기(item3/modify.jsp)
    *          - 여기까지 확인
    * 
    *          - 파일 업로드 수정 기능 컨트롤러 메소드 만들기(item3Modify:post)
    *          - 파일 업로드 수정 기능 서비스 인터페이스 메소드 만들기
    *          - 파일 업로드 수정 기능 서비스 클래스 메소드 만들기
    *          - 파일 업로드 수정 기능 Mapper 인터페이스 메소드 만들기
    *          - 파일 업로드 수정 기능 Mapper xml 쿼리 만들기
    *          - 여기까지 확인
    * 
    *          - 파일 업로드 삭제 화면 컨트롤러 메소드 만들기(item3RemoveForm:get)
    *          - 파일 업로드 삭제 화면 서비스 인터페이스 메소드 만들기
    *          - 파일 업로드 삭제 화면 서비스 클래스 메소드 만들기
    *          - 파일 업로드 삭제 화면 Mapper 인터페이스 메소드 만들기
    *          - 파일 업로드 삭제 화면 Mapper xml 쿼리 만들기
    *          - 파일 업로드 삭제 화면 만들기(item3/remove.jsp)
    *          - 여기까지 확인
    * 
    *          - 파일 업로드 삭제 기능 컨트롤러 메소드 만들기(item3Remove:post)
    *          - 파일 업로드 삭제 기능 서비스 인터페이스 메소드 만들기
    *          - 파일 업로드 삭제 기능 서비스 클래스 메소드 만들기
    *          - 파일 업로드 삭제 기능 Mapper 인터페이스 메소드 만들기
    *          - 파일 업로드 삭제 기능 Mapper xml 쿼리 만들기
    *          - 여기까지 확인
    * 
    */
	
	@Resource(name="uploadPath")
	private String resourcePath;
	
	@Inject
	private IItemService3 itemService;
	
	
	@GetMapping(value="/register")
	public String item3RegisterForm() {
		return "item3/register";
	}
	
	@RequestMapping(value="/uploadAjax", method=RequestMethod.POST,
			produces = "text/plain;charset=utf-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception{
		
		log.info("originalFileName : " + file.getOriginalFilename());
		
		// savedName은 /2024/03/06/UUID_원본파일명(오늘날짜/UUID_원본파일명) 을 리턴
		//resourcePath : 업로드까지의 폴더 경로
		//getOriginalFilename : 업로드한 파일 이름
		//getBytes : 업로드한 파일을 컴퓨터가 다시 읽을 수 있는 데이터로. 꼭 필요
		// UploadFileUtils 클래스를 만들어서 uploadFile 메서드를 만든다
		String savedName = UploadFileUtils.uploadFile(resourcePath, file.getOriginalFilename(), file.getBytes());
		//1. UUID 붙여서 /2024/03/06/ UUID_원본 파일명을 가진 파일 생성
		//2. 그 파일로 썸네일 이미지 만들기 위해 100X100으로 리사이징하고 s_ 붙여서 썸네일 생성
		
		return new ResponseEntity<String>(savedName, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping(value="/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName){
		
		//파일데이터를 바이트 배열로 써야해서 만들어놓고
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		log.info("fileName(displayFile) : " + fileName);
		
		//확장자 추출
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
		
		//미디어 타입 명시해야하니까
		MediaType mType = MediaUtils.getMediaType(formatName);
		
		//이 정보를 헤더에 담아서 알려줘야하니까 선언해주고
		HttpHeaders headers = new HttpHeaders();
		
		try {
			
			//파일 입력하려고 FileInputStream에 담는다(폴더경로+파일이름)
			in = new FileInputStream(resourcePath + fileName);
			
			
			if(mType != null) { //mType이 이미지면
				headers.setContentType(mType);
			}else { //mType이 이미지가 아니면
				
				//fileName.indexOf("_") uuid 다음의 언더바 이후의 '파일이름.확장자'를 가져옴
				fileName = fileName.substring(fileName.indexOf("_")+1);
				
				//APPLICATION_OCTET_STREAM : 기본 미디어타입
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				
				//이미지가 아닐 때 정해져 있는 방식 -> 다운로드 해준다
				headers.add("Content-Disposition", "attachment; filename=\""
						+ new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\""); 
			}
			
			//toByteArray : 바이트배열로 만들어준다
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),headers, HttpStatus.CREATED);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
			
		} finally {
			try {
				//FileInputStream 닫아준다
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return entity;
	}
	
	@PostMapping(value="/register")
	public String item3Register(Item3 item, Model model) {
		String[] files = item.getFiles();
		
		for(int i=0; i < files.length; i++) {
			log.info("files[i] : " + files[i]);
		}
		
		itemService.register(item);
		model.addAttribute("msg", "등록이 완료되었습니다!");
		return "item3/success";
	}
	
	@GetMapping(value="/list")
	public String item3List(Model model) {
		List<Item3> itemList = itemService.list();
		model.addAttribute("itemList", itemList);
		return "item3/list";
	}
	
	@GetMapping(value="/modify")
	public String item3ModifyForm(int itemId, Model model) {
		Item3 item = itemService.read(itemId);
		model.addAttribute("item", item);
		return "item3/modify";
	}
	
	@PostMapping(value="/modify")
	public String item3Modify(Item3 item, Model model) {
		String[] files = item.getFiles();
		
		for(int i=0; i < files.length; i++) {
			log.info("files[i] : " + files[i]);
		}
		
		itemService.modify(item);
		model.addAttribute("msg", "수정이 완료되었습니다!");
		return "item3/success";
	}
	
	@GetMapping(value="/remove")
	public String item3RemoveForm(int itemId, Model model) {
		Item3 item = itemService.read(itemId);
		model.addAttribute("item", item);
		return "item3/remove";
	}
	
	@PostMapping(value="/remove")
	public String item3Remove(int itemId, Model model) {
		itemService.remove(itemId);
		model.addAttribute("msg", "삭제가 완료되었습니다!");
		return "item3/success";
	}
	
	
	
	//JSON GET 방식으로 요청
	@ResponseBody
	@RequestMapping(value="/getAttach/{itemId}")
	public List<String> getAttach(@PathVariable("itemId") int itemId){
		log.info("itemId : " + itemId);
		return itemService.getAttach(itemId);
	}
}
