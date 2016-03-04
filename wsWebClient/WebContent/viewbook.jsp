<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ru">
<head>
<title>View book</title>
<%@ include file="jspf/headtag.jspf" %>
</head>
<body>
	<%@ include file="jspf/menu.jspf" %>
	<h2>${book.title}</h2>
	<hr/>
	<ul>
		<li><c:forEach var="a" items="${book.author}">${a}<br/></c:forEach></li>
		<li>${book.isbn}</li>
		<li>${book.price}</li>
		<li>${book.count}</li>
	</ul>
	<form action="AddToCard" method="post">
		<input type="hidden" name="id" value="${book.id}"/>
		<input type="number" min="1" max="${book.count}" name="count"/>
		<input type="submit" value="В корзину"/>
	</form>
	<c:if test="${not empty error}">
		<ul>
			<li>${error}</li>
		</ul>
	</c:if>
	<c:if test="${fn:length(errors) > 0}">
		<c:forEach var="err" items="${errors}">
			<ul>
				<li>${err}</li>
			</ul>
		</c:forEach>
	</c:if>

<%@ include file="jspf/bootstrap.jspf" %>
</body>
</html>