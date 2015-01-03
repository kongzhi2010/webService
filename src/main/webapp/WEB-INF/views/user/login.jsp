<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
  <script src="http://ajax.microsoft.com/ajax/jquery/jquery-1.4.min.js"></script>
  <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/views/scripts/user.js"></script>
  <script type="text/javascript">
    function verifyU(){
      verifyPath = ${pageContext.request.contextPath}+"/user/verifyUser";
      ajaxVerifyUser(verifyPath);
    }
  </script>
</head>
<body>

<div>
  <h2>a free demo</h2>

  <form id="register" method="post" action="${pageContext.request.contextPath}/user/login">
    <tr>
      <td><label>username</label>
        <input path="username" name="username" onclick="verifyU()" size="12" id="username"/>
        <span id="nameVerify"></span>
      </td>
    </tr>
    <br>
    <tr>
      <td><label>password</label>
        <input path="password" name="password" size="12" id="password" type="password"/>
      </td>
    </tr>
    <tr>
      <th></th>
      <td><input name="submit" type="submit" onclick="submit()" value="登录"/></td>
    </tr>
  </form>
</div>
</body>
</html>
