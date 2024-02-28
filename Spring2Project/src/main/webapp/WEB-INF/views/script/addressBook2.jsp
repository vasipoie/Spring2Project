<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css' integrity='sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN' crossorigin='anonymous'>
<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js'></script>
</head>
<body>
	<h1>주소록</h1>
	<hr/>
	<div class="row">
		<div class="col-md-12">
			<h4>MENU GROUP</h4>
			<button type="button" class="btn btn-info" onclick="javascript:location.href='/test/addAddress.do'">등록</button>
		</div>
	</div>
	<hr/>
	<div class="row">
		<div class="col-md-8">
			<div class="row" id="bookArea">
			</div>
		</div>
		<div class="col-md-4">
			<div class="card">
				<div class="card-header">즐겨찾기</div>
				<div class="card-body">
					[여자]백지은 010-2144-1234 <button type="button" class="btn btn-sm btn-danger">삭제</button><br/> 
					[여자]백지은 010-2144-1234 <button type="button" class="btn btn-sm btn-danger">삭제</button><br/>
					[여자]백지은 010-2144-1234 <button type="button" class="btn btn-sm btn-danger">삭제</button><br/>
				</div>
				<div class="card-footer">
					
				</div>
			</div> 
		</div>
	</div>
	<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">성공적으로 수행하였습니다</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	       	주소록에 성공적으로 등록하였습니다.
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
	      </div>
	    </div>
	  </div>
	</div>
</body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.2/js/bootstrap.min.js"></script>
<script>
let bookArea = document.querySelector('#bookArea');

var modal = new bootstrap.Modal(document.getElementById('myModal'));

//현재 url에서 쿼리스트링 부분(파라미터)을 찾아서 params에 넣어두고
//쿼리스트링이 있을 경우, status에 담고
//status에는 null또는 값이 담긴다
//조건을 통해 modal을 띄우고 이동
let params = new URLSearchParams(window.location.search);
let status = params.get("status");

if(status != null && status == 'true'){
	modal.show();
}

getList();

function getList() {
	$.ajax({
		url: 'getList.do',
		success: function(res) {
			
			let html = '';
			
			for(let data of res) {
				
				html += '<div class="col-md-4">';
				html += '	<div class="card">';
				html += '		<div class="card-header">';
				html += '			'+data.name+'';
				html += '		</div>';
				html += '		<div class="card-body">';
				html += '			<table class="table table-bordered">';
				html += '				<tr>';
				html += '					<td>이름</td>';
				html += '					<td>'+data.name+'</td>';
				html += '				</tr>';
				html += '				<tr>';
				html += '					<td>성별</td>';
				html += '					<td>'+data.gender+'</td>';
				html += '				</tr>';
				html += '				<tr>';
				html += '					<td>전화번호</td>';
				html += '					<td>'+data.phone1+'-'+data.phone2+'-'+data.phone3+'</td>';
				html += '				</tr>';
				html += '				<tr>';
				html += '					<td>직업</td>';
				html += '					<td>'+data.job+'</td>';
				html += '				</tr>';
				html += '			</table>';
				html += '		</div>';
				html += '		<div class="card-footer">';
				html += '			<button type="button" class="btn btn-primary">추가</button>';
				html += '		</div>';
				html += '	</div>';
				html += '</div>';
			}
			
			bookArea.innerHTML = html;
		}
	})

}
</script>
</html>