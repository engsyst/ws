<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/menu.jspf" %>
<%@ include file="/WEB-INF/jspf/search.jspf" %>
	<h1>Login</h1>
	<div><form action="login" method="post">
		<table>
			<tr><td colspan="2"><c:out value="${errors['login'] }"/></td></tr>
			<tr><td>Login:</td><td><input type="text" name="login" value="${user.login } "/></td></tr>
			<tr><td colspan="2"><c:out value="${errors['pass'] }"/></td></tr>
			<tr><td>Password:</td><td><input type="password" name="pass" value="${user.pass }" /></td></tr>
			<tr><td></td><td><input type="submit" value="Login"/></td></tr>
		</table>
	</form></div>
</body>
</html>