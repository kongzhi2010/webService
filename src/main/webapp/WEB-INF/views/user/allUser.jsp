<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title></title>
</head>
<body>
  <c:forEach var="user" items="${users}" >
    <table>
      <c:out value="尊敬的:${user.username}"></c:out>
      <br>
      <tr>您的密码是:${user.password}</tr>
      <br>
      <tr>您的邮箱是:${user.email}</tr>
    </table>
  </c:forEach>
</html>
