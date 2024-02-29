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
				<div class="card-body" id="bookmarkArea">
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
	let bookmarkArea = document.querySelector('#bookmarkArea');
	
	var modal = new bootstrap.Modal(document.getElementById('myModal'));
	
	//현재 url에서 쿼리스트링 부분(파라미터)을 찾아서(window.location.search -> 쿼리스트링 찾는거) params에 넣어두고
	//쿼리스트링이 있을 경우, status에 담고
	//status에는 null또는 값이 담긴다
	//조건을 통해 modal을 띄우고 이동
	let params = new URLSearchParams(window.location.search);
	let status = params.get("status");
	
	if(status != null && status == 'true'){
		modal.show();
	}
	
	viewRendering();
	
	function viewRendering(){
		getList();
		setTimeout(function(){
			getBookMarkList();
		},100);
	}
	
	function getBookMarkList(){
		//javascript 기존 비동기 방식으로 처리하던게 xhr
		//비동기 방식 리스트
		//xhr << 오래됨
		//fetch : promise 방식을 이식받아서 독특
		//흔히 사용하는게 jquery ajax
		//axios << 최신기술 굉장히 가볍고 좋음(단점 : 라이브러리 사용해야함)
		//사용빈도 : ajax > axios >= fetch > xhr
		//분포도 : ajax >= xhr
		
		$.ajax({
			url: 'getBookMarkList.do',
			success : function(res){
				
				let html = '';
				
				res.map((v)=>{
					html += '<span>['+v.gender+']'+v.name+' '+v.phone1 + '-' + v.phone2 + '-' + v.phone3+"</span>";
					html += '<button type="button" class="btn btn-sm btn-danger" data-no=' + v.no + '>삭제</button><br/>';
				});
				
				bookmarkArea.innerHTML = html;
			}
		});
	}
	
	function getList() {
		$.ajax({
			url: 'getList.do',
			success: function(res) {
				
				let html = '';
				
				for(let data of res) {
					//동적으로 번호에 맞춰서 id가 주어지고
					//아래 사용자정의 data도 동적으로 주어짐?
					html += '<div class="col-md-4" id="area-'+data.no+'">';
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
					html += '			<button type="button" class="btn btn-primary" data-no = "'+data.no+'">추가</button>';
					html += '		</div>';
					html += '	</div>';
					html += '</div>';
				}
				
				bookArea.innerHTML = html;
			}
		})
	
		
		bookArea.addEventListener('click', function(e){
			if(e.target.tagName === 'BUTTON'){
	// 			getBookMarkList(); //console에 1이 나오면 정상작동
				let no = e.target.dataset.no;
				let area = document.querySelector('#area-'+no);
				
				console.log(no, area);
				
// 				bookArea.removeChild(area);
				
				$.ajax({
					url: 'removeAddress.do',
					type: 'POST',
					data: JSON.stringify({
						id: no
					}),
					contentType: 'application/json; charset=UTF-8',
					success: function(res){
						if(res === 'success'){
							viewRendering();
						}
					}
				});
			}
		});
		
		
		bookmarkArea.addEventListener('click', function(e){
			if(e.target.tagName === 'BUTTON'){
				let no = e.target.dataset.no;
				let area = document.querySelectorAll('#bookmarkArea button[data-no]');
				
				//전개연산자 = [...area]안에 있는걸 하나씩 꺼낸다
				//area자체가 유사배열로 되어있기 때문에 배열에서 사용하는 메서드들을 사용할 수 없다
				//그렇기에 유사배열을 배열로 바꿔주기 위해서 전개연산자 사용
				//전개연산자[...] 
				//또 다른 방법으로는, Array.from()
				
				let areaArr = Array.from(area);
				console.log("areaArr : " , areaArr);
// 				areaArr.fileter(~~);
				
				[...area].filter(function(btn){
					console.log("btn", btn);
					if(btn.getAttribute('data-no')==no){
						bookmarkArea.removeChild(btn);
						return true;
					}
				})
				
				$.ajax({
					url: 'removeBookMark.do',
					type : 'post',
					contentType : 'application/json; charset=UTF-8',
					data : JSON.stringify({
						id : no
					}),
					success: function(res){
						console.log(res);
						if(res === 'success'){
							viewRendering();
						}
					}
				});
			}
		});
		
		
	}
</script>
</html>