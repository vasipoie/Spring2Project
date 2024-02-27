<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RegisterForm</title>
</head>
<body>
	<h1>REGISTER FORM</h1>
	<hr/>
	
	<h5>1. 컨트롤러 메소드 매개변수(요청 처리)</h5>
	
	<p>1) URL 경로 상의 쿼리 파라미터 정보로부터 요청 데이터를 취득할 수 있다.</p>
	<a href="/register?userId=hongkd&password=1234">Button01</a>
	<br/><br/>
	
	<p>2) URL 경로 상의 경로 변수로부터 요청 데이터를 취득할 수 있다.</p>
	<a href="/register/hongkd">Button02</a>
	<br/><br/>
	
	<p>3) HTML Form 필드명과 컨트롤러 매개변수명이 일치하면 요청 데이터를 취득할 수 있다.</p>
	<form action="/register01" method="post">
		userId : <input type="text" name="userId" value="hongkd"/><br/>
		password : <input type="text" name="password" value="1234"/><br/>
		coin : <input type="text" name="coin" value="100"/><br/>
		<input type="submit" value="전송"/><br/>
	</form><br/>
	<br/>
	
	<p>4) HTML Form 필드값이 숫자일 경우에는 컨트롤러 매개변수 타입이 숫자형이면 숫자로 타입변환하여 데이터를 취득하는가?</p>
	<form action="/register02" method="post">
		userId : <input type="text" name="userId" value="hongkd"/><br/>
		password : <input type="text" name="password" value="1234"/><br/>
		coin : <input type="text" name="coin" value="100"/><br/>
		<input type="submit" value="전송"/><br/>
	</form><br/>
	<br/>
	
	<h4>3. 요청 데이터 처리 어노테이션</h4>
	<hr/>
	
	<p>1) URL 경로 상의 경로 변수가 여러개일 때 @PathVariables 어노테이션을 사용하여 특정한 경로 변수명을 지정해준다.</p>
	<a href="/register/hongkd/100">Button03</a><br/>
	<br/>
	
	<p>2) @RequestParam 어노테이션을 사용하여 특정한 HTML Form의 필드명을 지정하여 요청을 처리한다.</p>
	<form action="/register0202" method="post">
		userId : <input type="text" name="userId" value="hongkd"/><br/>
		password : <input type="text" name="password" value="1234"/><br/>
		coin : <input type="text" name="coin" value="100"/><br/>
		<input type="submit" value="전송"/><br/>
	</form><br/>
	<br/>
	
	<h4>4. 요청 처리 자바빈즈</h4>
	<hr/>
	
	<p>1) 폼 텍스트 필드 요소값을 자바빈즈 매개변수로 처리한다.</p>
	<form action="/beans/register01" method="post">
		userId : <input type="text" name="userId" value="hongkd"/><br/>
		password : <input type="text" name="password" value="1234"/><br/>
		coin : <input type="text" name="coin" value="100"/><br/>
		<input type="submit" value="전송"/><br/>
	</form><br/>
	<br/>		
	
	<p>2) 여러 개의 폼 텍스트 필드 요소값을 매개변수 순서와 상관없이 매개변수명을 기준으로 처리한다.</p>
	<form action="/beans/register03" method="post">
		userId : <input type="text" name="userId" value="hongkd"/><br/>
		password : <input type="text" name="password" value="1234"/><br/>
		coin : <input type="text" name="coin" value="100"/><br/>
		<input type="submit" value="전송"/><br/>
	</form><br/>
	<br/>
	
	<h4>5. Data 타입 처리</h4>
	<hr/>
	
	<!-- 서버 쪽으로 넘어오기 이번에 요청이 끝나서 400에러 -->
	<p>1) 쿼리 파라미터(dateOfBirth=12341234)로 전달받은 값이 날짜 문자열 형식에 맞지 않아 Date 타입으로 변환에 실패한다.</p>
	<a href="/registerByGet01?userId=hongkd&dateOfBirth=12341234">Button04</a>
	
	<p>2) 쿼리 파라미터(dateOfBirth=2024-02-23)로 전달받았을 때 날짜로 변환이 되는가?</p>
	<a href="/registerByGet01?userId=hongkd&dateOfBirth=2024-02-23">Button05</a>
	
	<br/>
	
	<p>3) 쿼리 파라미터(dateOfBirth=2024/02/23)로 전달받았을 때 날짜로 변환이 되는가?</p>
	<a href="/registerByGet01?userId=hongkd&dateOfBirth=2024/02/23">Button06</a>
	
	<p>4) Member 매개변수와 쿼리 파라미터(dateOfBirth-20240223)로 전달받은 값이 날짜 문자열 형식으로 설정 시, 받는가?</p>
	<form action="/registerByGet02?dateOfBirth=20240223" method="post">
		userId : <input type="text" name="userId" value="hongkd"/><br/>
		password : <input type="text" name="password" value="1234"/><br/>
		coin : <input type="text" name="coin" value="100"/><br/>
		<input type="submit" value="전송"/><br/>
	</form><br/>
	<br/>
	
	
	<h4>7. 폼 방식 요청 처리</h4>
	<hr/>
	
	<p>1) 폼 텍스트 필드 요소 값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.</p>
	<form action="/registerUserId" method="post">
		userId : <input type="text" name="userId"/><br/>
		<input type="submit" value="전송"><br/>
	</form><br/>
	
	<p>2) 폼 비밀번호 필드 요소값을 자바빈즈 매개변수로 처리한다.</p>
	<form action="/registerPassword" method="post">
		password : <input type="password" name="password"/>
		<input type="submit" value="전송"><br/>
	</form><br/>
	
	<p>3) 폼 라디오버튼 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.</p>
	<form action="/registerRadio" method="post">
		gender : <br/>
		<input type="radio" name="gender" value="male" checked="checked"/>Male<br/>
		<input type="radio" name="gender" value="female"/>Female<br/>
		<input type="radio" name="gender" value="other"/>Other<br/>
		<input type="submit" value="전송"><br/>
	</form><br/>
	
	<hr/>
	
	<p>4) 폼 셀렉트 박스 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.</p>
	<form action="/registerSelect" method="post">
		nationality : <br/>
		<select name="nationality">
			<option value="korea">대한민국</option>
			<option value="germany">독일</option>
			<option value="austrailia">호주</option>
			<option value="canada">캐나다</option>
		</select><br/>
		<input type="submit" value="전송"/><br/>
	</form>
	<br/><br/>
	
	<p>5) 복수 선택이 가능한 폼 셀렉트 박스 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.</p>
	<form action="/registerMultipleSelect01" method="post">
		cars : <br/>
		<select name="cars" multiple="multiple">
			<option value="bmw">BMW</option>
			<option value="audi">AUDI</option>
			<option value="jeep">JEEP</option>
			<option value="volvo">VOLVO</option>
		</select><br/>
		<input type="submit" value="전송"/><br/>
	</form>
	<br/><br/>
	
	<p>6) 복수 선택이 가능한 폼 셀렉트 박스 요소값을 문자열 배열 타입 매개변수로 처리한다.</p>
	<form action="/registerMultipleSelect02" method="post">
		cars : <br/>
		<select name="carArray" multiple="multiple">
			<option value="bmw">BMW</option>
			<option value="audi">AUDI</option>
			<option value="jeep">JEEP</option>
			<option value="volvo">VOLVO</option>
		</select><br/>
		<input type="submit" value="전송"/><br/>
	</form>
	<br/><br/>
	
	<p>7) 복수 선택이 가능한 폼 셀렉트 박스 요소값을 문자열 요소를 가진 리스트 컬렉션 타입 매개변수로 처리한다.</p>
	<form action="/registerMultipleSelect03" method="post">
		cars : <br/>
		<select name="carList" multiple="multiple">
			<option value="bmw">BMW</option>
			<option value="audi">AUDI</option>
			<option value="jeep">JEEP</option>
			<option value="volvo">VOLVO</option>
		</select><br/>
		<input type="submit" value="전송"/><br/>
	</form><br/>
	
	<hr/>
	
	<p>8) 폼 체크박스 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.</p>
	<form action="/registerCheckbox01" method="post">
		hobby : <br/>
		<input type="checkbox" name="hobby" value="sports">Sports<br/>
		<input type="checkbox" name="hobby" value="music">Music<br/>
		<input type="checkbox" name="hobby" value="movie">Movie<br/>
		<input type="submit" value="전송"/><br/>
	</form>
	<br/><br/>
	
	<p>9) 폼 체크박스 요소값을 문자열 배열 타입 매개변수로 처리한다.</p>
	<form action="/registerCheckbox02" method="post">
		hobby : <br/>
		<input type="checkbox" name="hobbyArray" value="sports">Sports<br/>
		<input type="checkbox" name="hobbyArray" value="music">Music<br/>
		<input type="checkbox" name="hobbyArray" value="movie">Movie<br/>
		<input type="submit" value="전송"/><br/>
	</form>
	<br/><br/>
	
	<p>10) 폼 체크박스 요소값을 문자열 요소를 가진 리스트 컬렉션 타입 매개변수로 처리한다.</p>
	<form action="/registerCheckbox03" method="post">
		hobby : <br/>
		<input type="checkbox" name="hobbyList" value="sports">Sports<br/>
		<input type="checkbox" name="hobbyList" value="music">Music<br/>
		<input type="checkbox" name="hobbyList" value="movie">Movie<br/>
		<input type="submit" value="전송"/><br/>
	</form>
	<br/><br/>
	
	<p>11) 폼 체크박스 요소값을 기본 데이터 타입인 불리언 타입 매개변수로 처리한다.</p>
	<form action="/registerCheckbox04" method="post">
		foreigner : <br/>
		<input type="checkbox" name="foreigner" value="true">외국인 O<br/>
		<input type="submit" value="전송"/><br/>
	</form><br/>
	
	<hr/>
	
	<p>12) 폼 텍스트 필드 요소값을 중첩된 자바빈즈 매개변수로 처리한다.</p>
	<form action="/registerUserAddress" method="post">
	<!-- 
		멤버 vo에 postCode, location 필드는 없음
		address VO에 있지.
		그냥 name="postCode"이면, MemberVO에서 바인딩 할 값이 없어 null이 뜬다
		중첩된 자바빈즈 클래스 안에 넘겨받은 데이터를 바인딩 해야하는 상황이면, 
		name="address.postCode"면, MemberVO > AddressVO 안으로 들어감 > AddressVO에 명시되어있는 값을 바인딩 해줌
	 -->
		postCode : <input type="text" name="address.postCode"><br/>
		location : <input type="text" name="address.location"><br/>
		<input type="submit" value="전송"/><br/>
	</form>
	<br/><br/>
	
	<p>13) 폼 텍스트 필드 요소값을 중첩된 자바빈즈 매개변수로 처리한다.</p>
	<form action="/registerUserCardList" method="post">
		카드1 - 번호 : <input type="text" name="cardList[0].no"><br/>
		카드1 - 유효년월: <input type="text" name="cardList[0].validMonth"><br/>
		카드2 - 번호 : <input type="text" name="cardList[1].no"><br/>
		카드2 - 유효년월 : <input type="text" name="cardList[1].validMonth"><br/>
		<input type="submit" value="전송"/><br/>
	</form>
	<br/><br/>
	
	<hr/>
	<!--  -->
	
	<h3>8. 파일 업로드 폼 방식 요청 처리</h3>
	<hr/>
	
	<p>1) 파일업로드 폼 파일 요소값을 스프링 MVC가 지원하는 MultipartFile 매개변수로 처리한다.</p>
	<form action="/registerFile01" method="post" enctype="multipart/form-data">
		<input type="file" name="picture"/><br/>
		<input type="submit" value-"업로드"/>
	</form>
	<br/><br/>
	
	<p>2) 파일업로드 폼 파일 요소값과 텍스트 필드 요소값을 MultipartFile 매개변수와 문자열 매개변수로 처리한다.</p>
    <form action="/registerFile02" method="post" enctype="multipart/form-data">
    	userId : <input type="text" name="userId" value="hongkd"/><br/>
        password : <input type="text" name="password" value="1234"/><br/>
        <input type="file" name="picture"/><br/>
        <input type="submit" value="업로드" />
    </form>
    <br/><br/>
    
    <p>3) 파일업로드 폼 파일 요소값과 텍스트 필드 요소값을 MultipartFile 매개변수와 자바빈즈 매개변수로 처리한다.</p>
    <form action="/registerFile03" method="post" enctype="multipart/form-data">
    	userId : <input type="text" name="userId" value="hongkd"/><br/>
        password : <input type="text" name="password" value="1234"/><br/>
        <input type="file" name="picture"/><br/>
        <input type="submit" value="업로드" />
    </form>
    <br/><br/>
	
	<p>4) 파일업로드 폼 파일 요소값과 텍스트 필드 요소값을 FileMember 타입의 자바빈즈 매개변수로 처리한다.</p>
    <form action="/registerFile04" method="post" enctype="multipart/form-data">
    	userId : <input type="text" name="userId" value="hongkd"/><br/>
        password : <input type="text" name="password" value="1234"/><br/>
        <input type="file" name="picture"/><br/>
        <input type="submit" value="업로드" />
    </form>
    <br/><br/>
	
	<p>5) 여러 개의 파일업로드를 폼 파일 요소값을 여러 개의 MultipartFile 매개변수로 처리한다.</p>
    <form action="/registerFile05" method="post" enctype="multipart/form-data">
        <input type="file" name="picture"/><br/>
        <input type="file" name="picture2"/><br/>
        <input type="submit" value="업로드" />
    </form>
    <br/><br/>
	
	<p>6) 여러 개의 파일업로드를 폼 파일 요소값을 MultipartFile 타입의 요소를 가진 리스트 컬렉션 타입 매개변수로 처리한다.</p>
    <form action="/registerFile06" method="post" enctype="multipart/form-data">
        <input type="file" name="pictureList[0]"/><br/>
        <input type="file" name="pictureList[1]"/><br/>
        <input type="submit" value="업로드" />
    </form>
    <br/><br/>
	
	<p>7) 여러 개의 파일업로드를 폼 파일 요소값과 텍스트 필드 요소값을 MultiFileMember 타입의 자바빈즈 매개변수로 처리한다.</p>
    <form action="/registerFile07" method="post" enctype="multipart/form-data">
    	userId : <input type="text" name="userId" value="hongkd"/><br/>
        password : <input type="text" name="password" value="1234"/><br/>
        <input type="file" name="pictureList[0]"/><br/>
        <input type="file" name="pictureList[1]"/><br/>
        <input type="submit" value="업로드" />
    </form>
    <br/><br/>
    
	<p>8) 파일업로드 폼 파일 요소값과 텍스트 필드 요소값을 MultipartFile 타입의 배열 매개변수로 처리한다.</p>
    <form action="/registerFile08" method="post" enctype="multipart/form-data">
        <input type="file" name="pictureArray" multiple="multiple"/><br/>
        <input type="submit" value="업로드" />
    </form>
    <br/><br/>
    
    
	
	
	
</body>
</html>











