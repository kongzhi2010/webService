<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<div>
	<h2>a free demo</h2>
	<sf:form action="/webService/saveUser" method="POST" modelAttribute="user">
	<fieldset>
	<tr>
		<td><label>username</label>
		<sf:input path="username" size="12" id="username" />
		<small>not null!</small>
		<sf:errors path="username" />
		<span id="nameVerify"></span>
		</td>
		<br>
		<td><label>password</label>
		<sf:input path="password" size="12" id="password" /> 
		</td>
	</tr>
	<br>
	<tr>
		<th></th>
		<td><input name="submit" type="submit" value="I accept, please create my account!"/></td>
	</tr>
	</fieldset>
	</sf:form>
</div>