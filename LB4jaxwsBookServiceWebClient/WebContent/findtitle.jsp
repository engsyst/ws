<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List of books</title>
</head>
<body>
	<jsp:include page="jspf/head.jspf"></jsp:include>
 	<div>
 		<form action="findtitle" method="post">
 			<label for="pattern">Title: </label>
 			<input type="text" name="pattern" />
 			<input type="submit" value="Find" />
 		</form>
 	</div>
</body>
</html>