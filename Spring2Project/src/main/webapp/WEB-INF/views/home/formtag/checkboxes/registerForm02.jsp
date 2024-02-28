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
	<p>2) 모델에 List 타입의 데이터를 생성하여 추가한 후에 화면에 전달한다</p>
	<form:form modelAttribute="member" method="post" action="/formtag/register">
		<table>
			<tr>
				<td>취미(hobbyList)</td>
				<td>
					<!-- key는 input요소의 value로, value는 라벨로 -->
					<!-- itemLabel에는  hobbyCodeList의 프로퍼티 이름을 넣는다-->
					<form:checkboxes items="${hobbyCodeList }" itemLabel="label" itemValue="value" path="hobbyList"/><br/>
				</td>
			</tr>
		</table>
		<form:button name="register">등록</form:button>
	</form:form>
</body>
</html>