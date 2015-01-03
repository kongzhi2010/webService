<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
	<title>Getting Started: Serving Web Content</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<script src="http://ajax.microsoft.com/ajax/jquery/jquery-1.4.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/views/scripts/user.js"></script>
</head>
<body>
	<th>hello webService</th>
	<table>
		<tr><td><a href="${pageContext.request.contextPath}/userEdit" />点击注册</td></tr>
		<tr><td><a href="${pageContext.request.contextPath}/login" />用户登录</td></tr>
	</table>
</body>
</html>