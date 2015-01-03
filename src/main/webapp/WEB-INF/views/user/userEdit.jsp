<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <script src="http://ajax.microsoft.com/ajax/jquery/jquery-1.4.min.js"></script>
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/views/scripts/user.js"></script>
    <script type="text/javascript">
        function ajaxVUser(){
            path = "${pageContext.request.contextPath}/user/verifyUser";
            return ajaxVerifyUser(path)== true;
        }
        function customSubmit(){
            if(ajaxVUser()== true)
                return;
            $("#register").submit();
        }
    </script>
</head>
<body>

<div>
    <h2>a free demo</h2>

    <form id="register" method="post" action="${pageContext.request.contextPath}/user/saveUser" >
        <tr>
            <td><label>username</label>
                <input path="username" name="username" onblur="ajaxVUser()" size="12" id="username"/>
                <span id="nameVerify_register"></span>
            </td>
        </tr>
        <tr>
            <td><label>password</label>
                <input path="password" name="password" size="12" id="password" type="password"/>
            </td>
        </tr>
        <tr>
            <td><label>email </label>
                <input type="text" name="email" id="email" size="12"/>
                <span id="verifyEmail" style="display:none"></span>
            </td>
        </tr>
        <tr>
            <th></th>
            <td><input name="submit" type="submit" onclick="customSubmit()" value="注册"/></td>
        </tr>
    </form>
</div>
</body>
</html>
