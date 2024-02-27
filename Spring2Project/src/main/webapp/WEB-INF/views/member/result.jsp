<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RESULT</title>
</head>
<body>
	<h2>RESULT</h2>
		<table border="1">
			<tr>
				<td>유저 ID</td>
				<td>${member.userId}</td>
			</tr>
			<tr>
				<td>패스워드</td>
				<td>${member.password}</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>${member.userName}</td>
			</tr>
			<tr>
				<td>E-Mail</td>
				<td>${member.email}</td>
			</tr>
			<tr>
				<td>생년월일</td>
				<td>${member.dateOfBirth}</td>
			</tr>
			<tr>
				<td>성별</td>
				<td>${member.gender}</td>
			</tr>
			<tr>
				<td>개발자 여부</td>
				<td>${member.developer}</td>
			</tr>
			<tr>
				<td>외국인 여부</td>
				<td>${member.fore}</td>
			</tr>
			<tr>
				<td>국적</td>
				<td>${member.nationality}</td>
			</tr>
			<tr>
				<td>소유차량</td>
				<td>${member.cars }</td>
			</tr>
			<tr>
				<td>취미</td>
				<td>${member.hobby }</td>
			</tr>
			<tr>
				<td>우편번호</td>
				<td>${member.address.postCode}</td>
			</tr>
			<tr>
				<td>주소</td>
				<td>${member.address.location}</td>
			</tr>
			<c:forEach items="${member.cardList }" var="card" >
			
				<tr>
					<td>카드1 - 번호</td>
					<td>${card.no }</td>
				<tr>
					<td>카드1 - 유효년월</td>
					<td>${card.validMonth }</td>
				</tr>
				</tr>
			</c:forEach>
			<tr>
				<td>소개</td>
				<td>${member.introduction}</td>
			</tr>
			<tr>
				<td>개인정보 동의</td>
				<td>${member.ag}</td>
			</tr>
		</table>
</body>
</html>














