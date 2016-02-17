<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html">
<title>View book</title>
</head>
<body>
	<%@ include file="/WEB-INF/jspf/menu.jspf" %>
	<h2>${book.title}</h2>
	<hr/>
	<ul>
		<li><c:forEach var="a" items="${book.author}">${a}<br/></c:forEach></li>
		<li>${book.isbn}</li>
		<li>${book.price}</li>
		<li>${book.count}</li>
	</ul>
	<form action="BuyBook" method="post">
		<input type="hidden" name="id" value="${book.id}"/>
		<input type="number" min="1" max="${book.count}" name="count"/>
		<input type="submit" value="Добавить"/>
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
</body>
</html>