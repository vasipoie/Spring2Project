<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HOME0701</title>
</head>
<body>
	<!-- ${member.hobbyArray }는 배열이니까 var를 이용해서 하나씩 쓰겠다 -->
	<c:forEach items="${member.hobbyArray }" var="hobby">
		${hobby }<br/>
	</c:forEach>	
</body>
</html>