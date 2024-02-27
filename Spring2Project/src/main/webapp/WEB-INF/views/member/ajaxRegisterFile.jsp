<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AjaxRegisterFile</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<h1>10. 파일업로드 Ajax 방식 요청 처리</h1>
	<hr/>
	
	<p> Ajax 방식으로 전달한 파일 요소값을 스프링 MVC가 지원하는 MultipartFile 매개변수로 처리한다.</p>
	<div>
		<input type="file" id="inputFile"/><br/>
		<hr/>
		<img id="preview"/>
	</div>
	
	
	
</body>

<script type="text/javascript">
$(function(){
	   var inputFile = $('#inputFile');
	   
	   inputFile.on("change",function(e){
	      console.table("change event...!");
	      var files = e.target.files;
	      var file = files[0];
	      
	      console.table(file);
	      if(isImageFile(file)){
	         // 비동기 처리 시, 파일 데이터를 전송할 때에는 fromData()를 이용하여 데이터를 전송한다.
	         var formData = new FormData();
	         formData.append("file", file);
	         
	         //formData는 key/value 형식으로 데이터가 저장된다.
	         //dataType : 응답(response)데이터를 내보낼 때 보내줄 데이터 타입
	         //processData : 데이터 파라미터를 data라는 속성으로 넣는데, 제이쿼리 내부적으로 쿼리스트링을 구성합니다.
	         //				파일 전송의 경우 쿼리스트링을 사용하지 않으므로 해당 설정을 false로 비활성화합니다.
	         //contentType : Content-Type을 설정 시, 사용하는데 해당 설정의 기본값은
	         //				'application/x-www-form-urlencoded; charset=utf-8'
	         //				하여 기본값으로 나가지 않고 'multipart/form-data'로 나갈 수 있도록 설정을 false로 설정합니다.
	         //				request 요청에서 Content-Type을 확인해보면 'multipart/form-data; 
	         //				boundary===WebkitFormBoundary[HashKey~~~]'와 같은 값으로 전송된 것을 확인할 수 있다.
	         
	         
	         $.ajax({
	            type : "post",
	            url : "/ajax/uploadAjax",
	            data : formData,
	            //넘겨받은 데이터의 타입이 " SUCCESS". text임
	            dataType : "text",
	            processData : false,
	            contentType : false,
	            success : function(data){
	               console.table(data);
	               if(data ==="UPLOAD SUCCESS"){
	                  var file = e.target.files[0];
	                  var reader = new FileReader();
	                  reader.onload = function(event){
	                     $('#preview').attr("src", event.target.result);
	                  }
	                //base64로 인코딩된 결과값이 e에 담겨져서 e.target.result로 넘어옴
	                  reader.readAsDataURL(file);
	               }
	            }
	         });
	      }else { //선택한 파일이 이미지 파일이 아닌 경우
	    	  alert("이미지 파일을 넣어주세요!");
	      }
	   });

function isImageFile(file){
   //file.name = 파일명, pop() = 꺼냄, toLowerCase() = 소문자로 변경
   var ext = file.name.split(".").pop().toLowerCase(); // 파일명에서 확장자를 가져온다.
   // 확장자 중 이미지에 해당하는 확장자가 아닌경우 포함되어 있는 문자가 없으니까 -1(false) 가 리턴
   // 확장자 중 이미지에 해당하는 확장자가 포함되어 있다면 0보다 큰 수 일테니 true 가 리턴
   return ($.inArray(ext, ["jpg","jpeg","png","gif"]) === -1) ? false : true;
}
});                  
                 
</script>
</html>