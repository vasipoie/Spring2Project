package kr.or.ddit.controller.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/test")
public class TestController {
	
	private List<String> imageList;
	
	@PostConstruct
	public void init() {
		String[] imageFileName = {
			"audi01.png",
			"audi02.png",
			"audi03.png",
			"audi04.png",
			"bmw.png",
			"bmw01.jpg",
			"bmw02.jpg",
			"bmw03.jpg",
			"bmw04.jpg",
			"bmw05.jpg",
			"gif01.gif",
			"gif02.gif",
			"gif03.gif",
			"gif04.gif",
			"jeep01.jpg",
			"jeep02.jpg",
			"jeep03.jpg",
			"jeep04.jpg",
			"jeep05.jpg",
			"jeep06.jpg"
		};
		imageList = new ArrayList<String>();
		for(int i = 0; i < imageFileName.length; i++) {
			imageList.add(imageFileName[i]);
		}
	}
	
	@RequestMapping(value="/test01.do")
	public String test(Model model) {
		model.addAttribute("imageFileList", imageList);
		return "script/test01";
	}
	
	@RequestMapping(value="/{type}", method=RequestMethod.PUT)
	public ResponseEntity<List<String>> selectList(
			@PathVariable String type
			){
		
		List<String> selectList = new ArrayList<String>();
		
		if(type.equals("all")) {
			return new ResponseEntity<List<String>>(imageList, HttpStatus.OK); 
		}else {
			for(int i = 0; i < imageList.size(); i++) {
				if(imageList.get(i).endsWith(type)) {
					selectList.add(imageList.get(i));
				}
			}
		}
		return new ResponseEntity<List<String>>(selectList, HttpStatus.OK);
	}
	
	
	
	
	
}
