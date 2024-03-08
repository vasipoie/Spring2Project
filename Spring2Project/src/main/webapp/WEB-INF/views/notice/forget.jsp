<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="">
	<div class="card card-outline card-primary">
		<div class="card-header text-center">
			<p class="h4">
				<b>아이디찾기</b>
			</p>
		</div>
		<div class="card-body">
			<p class="login-box-msg">아이디 찾기는 이메일, 이름을 입력하여 찾을 수 있습니다.</p>
			<form action="" method="post">
				<div class="input-group mb-3">
					<input type="text" class="form-control" id="memEmail" placeholder="이메일을 입력해주세요.">
				</div>
				<div class="input-group mb-3">
					<input type="text" class="form-control" id="memName" placeholder="이름을 입력해주세요.">
				</div>
				<div class="input-group mb-3">
					<p>
						회원님의 아이디는 [<span id="existId"></span>] 입니다.
					</p>
				</div>
				<div class="row">
					<div class="col-12">
						<button type="button" class="btn btn-primary btn-block" id="findIdBtn">아이디찾기</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<br />
	<div class="card card-outline card-primary">
		<div class="card-header text-center">
			<p href="" class="h4">
				<b>비밀번호찾기</b>
			</p>
		</div>
		<div class="card-body">
			<p class="login-box-msg">비밀번호 찾기는 아이디, 이메일, 이름을 입력하여 찾을 수 있습니다.</p>
			<form action="/notice/pwForget.do" method="post">
				<div class="input-group mb-3">
					<input type="text" class="form-control" id="memId" placeholder="아이디를 입력해주세요.">
				</div>
				<div class="input-group mb-3">
					<input type="text" class="form-control" id="memEmail2" placeholder="이메일을 입력해주세요.">
				</div>
				<div class="input-group mb-3">
					<input type="text" class="form-control" id="memName2" placeholder="이름을 입력해주세요.">
				</div>
				<div class="input-group mb-3">
					<p>
						회원님의 비밀번호는 [<span id="existPw"></span>] 입니다.
					</p>
				</div>
				<div class="row">
					<div class="col-12">
						<button type="button" class="btn btn-primary btn-block" id="findPwBtn">비밀번호찾기</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<br/>
	<div class="card card-outline card-secondary">
		<div class="card-header text-center">
			<h4>MAIN MENU</h4>
			<button type="button" class="btn btn-secondary btn-block">로그인</button>
		</div>
	</div>
</div>

<script type="text/javascript">
$(function(){
	var memName = $('#memName');
	var memEmail = $('#memEmail');
	
	var memId = $('#memId');
	var memEmail2 = $('#memEmail2');
	var memName2 = $('#memName2');
	
	var findIdBtn = $('#findIdBtn');
	var findPwBtn = $('#findPwBtn');
	
	findIdBtn.on("click", function(){
		var email = memEmail.val();
		var name = memName.val();
		
		if(email == null || email == ""){
			alert("이메일을 입력해주세요!");
			memEmail.focus();
			return false;
		}
		
		if(name == null || name == ""){
			alert("이름을 입력해주세요!");
			memName.focus();
			return false;
		}
		
		var data = {
				memEmail : email,
				memName : name
		}
		
		$.ajax({
			type : "post",
			url : "/notice/idForget.do",
			data : JSON.stringify(data),
			contentType : "application/json; charset=utf-8",
			success : function(res){
				console.log(res);
				$("#existId").text(res);
			}
		});
		
	});
	
	findPwBtn.on("click", function(){
		var id = memId.val();
		var email = memEmail2.val();
		var name = memName2.val();
		
		if(id == null || id == ""){
			alert("아이디를 입력해주세요!");
			memId.focus();
			return false;
		}
		
		if(email == null || email == ""){
			alert("이메일을 입력해주세요!");
			memEmail2.focus();
			return false;
		}
		
		if(name == null || name == ""){
			alert("이름을 입력해주세요!");
			memName2.focus();
			return false;
		}
		
		var data = {
				memId : id,
				memEmail : email,
				memName : name
		}
		
		$.ajax({
			type : "post",
			url : "/notice/pwForget.do",
			data : JSON.stringify(data),
			contentType : "application/json; charset=utf-8",
			success : function(res){
				console.log(res);
				$("#existPw").text(res);
			}
		});
		
	});
	
	
	
	
});
</script>