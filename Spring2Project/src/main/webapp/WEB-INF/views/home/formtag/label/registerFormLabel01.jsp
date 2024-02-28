<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Spring Form</h2>
	<form:form modelAttribute="member" method="post" action="/formtag/label/result">
		<table>
			<tr>
				<!-- label은 텍스트를 클릭해도 체크되게, 체크박스를 클릭해도 체크되게 해준다 -->
				<td><form:label path="userId">유저ID</form:label></td>
				<td>
					<form:input path="userId"/>
					<font color="red">
						<form:errors name="userId" />
					</font>
				</td>
			</tr>
		</table>
		<form:button name="register">등록</form:button>
	</form:form>
</body>
</html>