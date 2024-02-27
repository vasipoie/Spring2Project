<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<select class="form-control" id="selectImgType">
		<option value="all">전체</option>
		<option value="jpg">JPG</option>
		<option value="png">PNG</option>
		<option value="gif">GIF</option>
	</select>
	
	<hr/>
	<div class="row" id="imageArea">
		<c:choose>
			<c:when test="${empty imageFileList }">
				<h1>이미지 파일이 존재하지 않습니다.</h1>
			</c:when>
			<c:otherwise>
				<c:forEach items="${imageFileList }" var="imageFile">
					<div class="col-md-3">
						<div class="card">
							<div class="card-header">${imageFile }</div>
							<div class="card-body">
								<img src="${pageContext.request.contextPath }/resources/image/${imageFile}"
									style="width:200px;"/>
							</div>	
							<div class="card-footer">
								<a class="btn btn-primary" href="${pageContext.request.contextPath }/resources/image/${imageFile}" download>다운로드</a>
							</div>					
						</div>
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</div>
</body>
<script type="text/javascript">
$(function(){
	var selectImgType = $("#selectImgType");
	var imgFile = $("#imgFile");
	var imageArea = $("#imageArea");
// 	var header = document.getElementsByClassName('card-header')[0];
// 	console.log(">>",header)
	
	selectImgType.on("change", function(){
		var type = selectImgType.val();
		
		$.ajax({
			url : "/test/"+ type,
			type : "put",
			contentType : "application/json; charset=utf-8",
			success : function(result){
// 				console.log("result : "+result);
				
				var html = "";
				
				for(var i=0; i<result.length; i++){
// 					console.log(result[i]);
					html += "<div class='col-md-3'>";
					html += "<div class='card'>";
					html += "<div class='card-header'>";
					html += result[i];
					html += "</div>";
					html += "<div class='card-body'>";
					html += "<img src='${pageContext.request.contextPath }/resources/image/"
					html += result[i]
					html += "' style='width:200px;'/>";
					html += "</div>";
					html += "<div class='card-footer'>";
					html += "<a class='btn btn-primary' href='${pageContext.request.contextPath }/resources/image/"
					html += result[i]
					html += "' download>다운로드</a>";
					html += "</div>";
					html += "</div>";
					html += "</div>";
				}
				imageArea.html(html);
			}
		});
	});
});
	


</script>
</html>