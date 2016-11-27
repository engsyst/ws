<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List of books</title>
</head>
<body>
	<jsp:include page="jspf/head.jspf"></jsp:include>
 	<c:choose>
 	<c:when test="${empty books }">
		<h2>No books found</h2>
 	</c:when>
	<c:otherwise>
	<table>
	<thead>
		<tr>
			<td>Title</td>
			<td>Authors</td>
			<td>ISBN</td>
			<td>Price</td>
			<td>Count</td>
			<td>Action</td>
		</tr>
	</thead>
	<c:forEach var="book" items="${books }">
		<tr>
			<td>${book.title }</td>
			<td>
			<ul>
			<c:forEach var="author" items="${book.author }">
				<li>${author }</li>
			</c:forEach>
			</ul>
			</td>
			<td>${book.isbn }</td>
			<td>${book.price }</td>
			<td>${book.count }</td>
			<td><form action="delete" method="post">
				<input type="hidden" name="id" value="${book.id }">
				<input type="submit" value="Delete">
			</form> </td>
		</tr>
	</c:forEach>
	</table>
	</c:otherwise>
	</c:choose>
</body>
</html>