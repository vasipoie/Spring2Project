<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>AdminLTE 3 | Log in</title>

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/plugins/fontawesome-free/css/all.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/dist/css/adminlte.min.css">
<script src="${pageContext.request.contextPath }/resources/plugins/jquery/jquery.min.js"></script>
</head>
<!-- ${bodyText } : 페이지가 바뀔 때마다 가지고 올 class명 -->
<body class="hold-transition ${bodyText }" 
	style="background-image: url('${pageContext.request.contextPath }/resources/dist/img/background04.jpg');background-size: cover;">
	<!-- content 영역 -->
	<tiles:insertAttribute name="content"/>
	
	<script src="${pageContext.request.contextPath }/resources/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath }/resources/dist/js/adminlte.min.js"></script>
</body>
</html>
