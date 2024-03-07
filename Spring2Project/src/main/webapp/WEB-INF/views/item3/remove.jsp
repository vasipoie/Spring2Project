<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ITEM REMOVE</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
   <h2>REMOVE</h2>
   <form action="/item3/remove" method="post">
      <input type="hidden" name="itemId" value="${item.itemId }"/>
      <table border="1">
         <tr>
            <td>상품명</td>
            <td><input type="text" name="itemName" id="itemName" value="${item.itemName }" disabled="disabled"/></td>
         </tr>
         <tr>
            <td>가격</td>
            <td><input type="text" name="price" id="price" value="${item.price }" disabled="disabled"/></td>
         </tr>
         <tr>
            <td>파일</td>
            <td>
               <div class="uploadedList"></div>
            </td>
         </tr>
         <tr>
            <td>개요</td>
            <td><textarea rows="10" cols="30" name="description" disabled="disabled">${item.description }</textarea> </td>
         </tr>
      </table>
      <div>
         <button type="button" id="listBtn" onclick="javascript:location.href='/item3/list'">List</button>
         <button type="submit" id="removeBtn">Remove</button>
      </div>
   </form>
</body>
<script>
	
	$(function(){
		
		var itemId = ${item.itemId};
		console.log("itemId: " + itemId);
		
		//내역 만듦
		$.getJSON("/item3/getAttach/" + itemId, function(list){
			$(list).each(function(){
				console.log("data >>>  " + this);
				var data = this;
				var str = "";
				if(checkImageType(data)){ //기존에 있던 데이터 불러와서 출력
					str+="<div>";
					str+="	<a href='/item3/displayFile?fileName=" + data + "'>";
					str+="		<img src='/item3/displayFile?fileName=" + 
									getThumbnailName(data) + "'>";
					str+="	</a>";
					str+="</div>";
				} else {
					str+="<div>";
					str+=" <a href='/item3/displayFile?fileName=" + data + "'>";
					str+="		" + getOriginalName(data);
					str+="	</a>";
					str+="</div>";
				}
				
				console.log("")
				$(".uploadedList").append(str);
		});
		
		//파일명 추출
		function getOriginalName(fileName){
			if(checkImageType(fileName)){
				return;
			}
			var idx = fileName.indexOf("_") + 1;
			return fileName.substr(idx);
		}
		
		// 임시 파일로 썸네일 이미지 생성
		function getThumbnailName(fileName){
			var front = fileName.substr(0, 12); // 년 월 일 에 해당하는 폴더 경로
			var end = fileName.substr(12);		// 뒤쪽 파일명
			
			console.log(front);
			console.log(end);
			
			return front + "s_" + end;
		}
		
		//이미지 파일인지 확인
		function checkImageType(fileName){
			//정규식에서 i는 대소문자 구분없음을 나타냄
			var pattern = /jpg|gif|png|jpeg/i;
			return fileName.match(pattern); //패턴과 일치하면 이미지로 판단
		}
	});
		
});
</script>
</html>












