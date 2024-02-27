<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Read02</title>
</head>
<body>
	<h3>RESULT</h3>
	<!-- 
		member : Member 클래스가 소문자 member로 바뀐 것 
		userId : getter메소드인데, el표현문 안에서는 get이 날라가고 필드 형태처럼 쓰여진다
	-->
	member.userId : ${member.userId }<br/>
	member.password : ${member.password }<br/>
	member.userName : ${member.userName }<br/>
	member.email : ${member.email }<br/>
	member.birthDay : ${member.birthDay }<br/>
</body>
</html>