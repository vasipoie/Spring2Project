<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ITEM2 REGISTER</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<h2>REGISTER</h2>
	<form action="/item3/register" method="post" id="item3" enctype="multipart/form-data">
		<table border="1">
			<tr>
				<td>상품명</td>
				<td><input type="text" name="itemName" id="itemName"/></td>
			</tr>
			<tr>
				<td>가격</td>
				<td><input type="text" name="price" id="price"/></td>
			</tr>
			<tr>
				<td>파일</td>
				<td>
					<input type="file" id="inputFile"/>
					<div class="uploadedList"></div>
				</td>
			</tr>
			<tr>
				<td>개요</td>
				<td><textarea rows="10" cols="30" name="description"></textarea></td>
			</tr>
		</table>
		<div>
			<button type="button" id="listBtn" onclick="javascript:location.href='/item3/list'">List</button>
			<button type="submit" id="regitserBtn">Register</button>
		</div>
	</form>
</body>
<script type="text/javascript">
$(function(){
	var inputFile = $('#inputFile');	//input file element
	var item3 = $('#item3');			//form element
	
	item3.submit(function(){
		var that = $(this);		//현재 누른 form태그
		var str = "";
		//a태그를 전부 가져와서
		$(".uploadedList a").each(function(index){
			//a태그 안에 있는 href를 
			var value = $(this).attr("href");
			value = value.substr(28);	// '?fileName=' 다음에 나오는 값
					
			str += "<input type='hidden' name='files["+index+"]' value='"+value+"'>";
		});
		
		console.log("str = " + str);
		that.append(str);
	});
	
	//open파일을 변경했을 때 발동
	inputFile.on('change', function(){
		console.log("change event...!");
		
		var files = event.target.files;
		var file = files[0];
		
		console.log(file);	//파일 체크
		//비동기로 파일전송시 반드시 필요
		var formData = new FormData();
		formData.append("file", file);
		
		$.ajax({
			url : "/item3/uploadAjax",
			type : "post",
			data : formData,
			dataType : "text",
			/* 쿼리스트링 구현 안할거니까 false */
			processData : false,
			/* multipart 쓸 거니까 false */
			contentType : false,
			success : function(data){
				console.log(data);
				
				var str = "";
				if(checkImageType(data)){	//이미지면 이미지태그를 이용하여 출력
					str += "<div>";
					str += "	<a href='/item3/displayFile?fileName=" + data +"'>";
					str += "		<img src='/item3/displayFile?fileName=" 
							+ getThumbnailName(data) + "'>";
					str += "	</a>";
					str += "	<span>X</span>";
					str += "</div>";
				}else {
					str += "<div>";
					str += "	<a href='item3/displayFile?fileName=" + data + "'>";
					str += "	" + getOriginalName(data);
					str += "	</a>";
					str += "	<span>X</span>";
					str += "<div>";
				}
				
				$(".uploadedList").append(str);
			}
		});
	});
	
	// 업로드 한 파일의 'X'를 클릭했을 때
	$(".uploadedList").on('click', 'span', function(){
		$(this).parent("div").remove();
	});
	
	// 파일명 추출
	function getOriginalName(fileName){
		if(checkImageType(fileName)){
			return;
		}
		var idx = fileName.indexOf("_") + 1;
		return fileName.substr(idx);
	}
	
	// 임시 파일로 썸네일 이미지 만들기
	function getThumbnailName(fileName){
		//원본 파일명을 기준으로 s_를 붙여주기 위해 front와 end를 나눈다
		var front = fileName.substr(0,12);	// /2024/03/06/ 폴더
		var end = fileName.substr(12);	// 뒤 파일명
		
		console.log("front : " + front);
		console.log("end : " + end);
		
		return front + "s_" + end;
	}
	
	// 이미지 파일인지 확인
	function checkImageType(fileName){
		// i : 정규표현식 플래그. 대소문자를 구분x
		var pattern = /jpg|gif|png|jpeg/i;
		return fileName.match(pattern);	//패턴과 일치하면 true(너 이미지구나?)
	}
});
</script>
</html>